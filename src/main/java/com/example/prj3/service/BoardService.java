package com.example.prj3.service;

import com.example.prj3.dto.Board;
import com.example.prj3.mapper.BoardMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
@RequiredArgsConstructor
@Transactional(rollbackFor = Exception.class)
public class BoardService {

    @Autowired
    private BoardMapper mapper;

    public Board getBoard(Integer id) {
        return mapper.selectById(id);
    }

    public boolean update(Board board, MultipartFile[] files, List<String> removeFiles) throws IOException {
        // boardFile 삭제
        if (removeFiles != null && !removeFiles.isEmpty()) {
            for (String fileName : removeFiles) {
                // 하드 디스크에서 삭제
                String path = "C:" + board.getId() + "\\" + fileName;
                // 테이블에서 삭제
                mapper.deleteBoardFileByBoardIdAndFileName(board.getId(), fileName);
            }
        }

        // 새 파일 추가
        for (MultipartFile file : files) {
            if (file.getSize() > 0) {
                // 테이블에 파일명 추가
                mapper.insertFile(board.getId(), file.getOriginalFilename());

                String fileName = file.getOriginalFilename();
                String folder = "C:\\study\\upload\\" + board.getId();
                String path = folder + "\\" + fileName;

                // 디렉토리 없으면 만들기
                File dir = new File(folder);
                if (!dir.exists()) {
                    dir.mkdirs();
                }

                // 파일을 하드디스크에 저장
                File file1 = new File(path);
                file.transferTo(file1);
            }
        }

        // 게시물 테이블 수정
        int cnt = mapper.update(board);

        return cnt == 1;
    }

    public boolean remove(Integer id) {
        // 파일명 조회
        List<String> fileNames = mapper.selectBoardFileByBoardId(id);

        // 파일 삭제
        mapper.deleteBoardFileByBoardId(id);

        // 하드 디스크의 파일 지우기
        for (String fileName : fileNames) {
            String path = "C:\\study\\upload\\" + id + "\\" + fileName;
            File file = new File(path);
            if (file.exists()) {
                file.delete();
            }
        }

        // 게시물 테이블 삭제
        int cnt = mapper.remove(id);

        return cnt == 1;
    }

    public boolean addBoard(Board board, MultipartFile[] files) throws Exception {
        // 게시물 insert
        int cnt = mapper.insert(board);

        // 강제로 익셉션 발생 시켜 주는 코드
//        if (true) {
//            throw new Exception("테스트용");
//        }

        for (MultipartFile file : files) {
            if (file.getSize() > 0) {
            // 파일 저장
            // 폴더 만들기
            String folder = "C:\\study\\upload\\" + board.getId();
            File targetFolder = new File(folder);
            if (!targetFolder.exists()){
                targetFolder.mkdirs();
            }

            String path = folder + "\\" + file.getOriginalFilename();
            File target = new File(path);
            file.transferTo(target);
            // db에 관련 정보 저장
            mapper.insertFile(board.getId(), file.getOriginalFilename());
            }
        }

        return cnt == 1;
    }

    public Map<String, Object> listBoard(Integer page, String search, String category) {
        // 페이지당 레코드의 수
        int rowPerPage = 15;

        Integer startIndex = (page - 1) * rowPerPage;
        // 페이징
        // 전체 레코드 수
        Integer numOfRecord = mapper.countAll(search, category);
        // 마지막 페이지 번호
        int lastPageNum = (numOfRecord - 1) / rowPerPage + 1;
        int firstPageNum = 1;

        // 페이지네이션 왼쪽 번호
        int leftPageNum = page - 5;
            // 1보다 작을 수 없다
        leftPageNum = Math.max(leftPageNum, 1);

        // 페이지네이션 오른쪽 번호
        int rightPageNum = leftPageNum + 9;
            // 마지막 페이지 보다 클 수 없다
        rightPageNum = Math.min(rightPageNum, lastPageNum);

        Map<String, Object> pageInfo = new HashMap<>();
        pageInfo.put("rightPageNum", rightPageNum);
        pageInfo.put("leftPageNum", leftPageNum);
        pageInfo.put("currentPageNum", page);
        pageInfo.put("lastPageNum", lastPageNum);
        pageInfo.put("firstPageNum", firstPageNum);

        // 게시물 정보
        List<Board> list = mapper.selectAllPaging(startIndex, rowPerPage, search, category);

        return Map.of("pageInfo", pageInfo, "boardList", list);
    }
}
