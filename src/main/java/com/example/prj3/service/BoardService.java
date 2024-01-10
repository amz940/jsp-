package com.example.prj3.service;

import com.example.prj3.dto.Board;
import com.example.prj3.mapper.BoardMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
@RequiredArgsConstructor
public class BoardService {

    @Autowired
    private BoardMapper mapper;

    public Board getBoard(Integer id) {
        return mapper.selectById(id);
    }

    public boolean update(Board board) {
        int cnt = mapper.update(board);

        return cnt == 1;
    }

    public boolean remove(Integer id) {
        int cnt = mapper.remove(id);

        return cnt == 1;
    }

    public boolean addBoard(Board board) {
        int cnt = mapper.insert(board);

        return cnt == 1;
    }

    public Map<String, Object> listBoard(Integer page) {
        // 페이지당 레코드의 수
        int rowPerPage = 15;

        Integer startIndex = (page - 1) * rowPerPage;
        // 페이징
        // 전체 레코드 수
        Integer numOfRecord = mapper.countAll();
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
        List<Board> list = mapper.selectAllPaging(startIndex, rowPerPage);

        return Map.of("pageInfo", pageInfo, "boardList", list);
    }
}
