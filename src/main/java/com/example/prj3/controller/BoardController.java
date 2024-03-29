package com.example.prj3.controller;

import com.example.prj3.dto.Board;
import com.example.prj3.mapper.BoardMapper;
import com.example.prj3.service.BoardService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import software.amazon.awssdk.regions.Region;
import software.amazon.awssdk.services.s3.S3Client;
import software.amazon.awssdk.services.s3.model.PutObjectRequest;

import java.io.IOException;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/")
public class BoardController {

    @Autowired
    private BoardService service;

    // 게시물 작성 form ( view) 포워드
    @GetMapping("add")
    public void addForm(Board board) {

    }

    // 새 게시물 db에 추가
    @PostMapping("add")
    public String addProcess(Board board, RedirectAttributes rttr,
                             @RequestParam(value = "files", required = false)MultipartFile[] files) throws Exception {
        // 1.
        // 2.
        boolean ok = service.addBoard(board, files);
        // 3.
        if (ok) {
            rttr.addFlashAttribute("message", board.getId()+"번 게시물이 등록되었습니다");
            return "redirect:/id/" + board.getId();
        } else  {
            rttr.addFlashAttribute("message", "게시물 등록중 문제가 발생했습니다");
            rttr.addFlashAttribute("board", board);
            return "redirect:/add";
        }
    }

    // 게시물 목록
    @GetMapping("list")
    public String list(Model model, @RequestParam(value = "page" ,defaultValue = "1") Integer page,
                       @RequestParam(value = "search", defaultValue = "")String search,
                       @RequestParam(value = "category", defaultValue = "all")String category) {
        // 1. request param  수집/가공
        // 2. business logic 처리
//        List<Board> list = service.listBoard();
        Map<String, Object> result = service.listBoard(page, search, category);
        // 3. add attribute
        // 각 각 개별적으로 받아서 model에 담는다
//        model.addAttribute("boardList", result.get("boardList"));
//        model.addAttribute("pageInfo", result.get("pageInfo"));
        // 여러개를 model에 담을땐 allAttribute를 써서 한번에 담는다
        model.addAllAttributes(result);
        // 4. forward/redirect
        return "list";
    }

    // 게시글 불러오기
    @GetMapping("/id/{id}")
    public String board(@PathVariable("id") Integer id, Model model) {
        // 1. request param
        // 2. business logic
        Board board = service.getBoard(id);
        System.out.println(board);
        // 3. add attribute
        model.addAttribute("board", board);
        // 4. forward/redirect
        return "get";
    }

    @GetMapping("link1")
    public void method1(){

        Region region = Region.AP_NORTHEAST_2;
        String bucketName = "amz940";

        S3Client s3 = S3Client.builder()
                .region(region)
                .build();

//        PutObjectRequest objectRequest = PutObjectRequest.builder()
//                .bucket(bucketName)
//                .key()
//                .build();
    }

    // 게시글 수정
    // jsp 화면 이동용 으로 사용
    @GetMapping("/update/{id}")
    public String update(@PathVariable("id") Integer id, Model model) {
        model.addAttribute("board", service.getBoard(id));
        return "update";
    }

    // 게시글 수정하기
    // db에서 데이터 처리용
    @PostMapping("/update/{id}")
    public String updateProcess(Board board, RedirectAttributes rttr,
                                @RequestParam(value = "files", required = false) MultipartFile[] files,
                                @RequestParam(value = "removeFiles", required = false) List<String> removeFiles) throws IOException {
        boolean ok = service.update(board, files, removeFiles);

        if (ok) {
            // 해당 게시물 보기로 리디렉션
//            rttr.addAttribute("success", "success");
            rttr.addFlashAttribute("message", board.getId()+ "번 게시물이 수정되었습니다.");
            return "redirect:/id/" + board.getId();
        } else {
            // 수정 form으로 리디렉션
//            rttr.addAttribute("fail", "fail");
            rttr.addFlashAttribute("message", board.getId() + "번 게시물이 수정되지 않았습니다");
            return "redirect:/update/" + board.getId();
        }

    }

    // 게시글 삭제하기
    @PostMapping("/remove")
    public String remove(Integer id, RedirectAttributes rttr) {
        boolean ok = service.remove(id);

        if(ok) {
            // query string에 추가
//            rttr.addAttribute("success", "remove");
            rttr.addFlashAttribute("message", id + "번 게시물이 삭제되었습니다.");
            return "redirect:/list";
        } else {
            rttr.addAttribute("fail", "fail");
            return "redirect:/id/" + id;
        }
    }
}
