package com.example.prj3.controller;

import com.example.prj3.dto.Board;
import com.example.prj3.mapper.BoardMapper;
import com.example.prj3.service.BoardService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;

@Controller
@RequestMapping("/")
public class BoardController {

    @Autowired
    private BoardService service;
    // 게시물 목록
    @GetMapping("list")
    public String list(Model model) {
        // 1. request param  수집/가공
        // 2. business logic 처리
        List<Board> list = service.listBoard();
        // 3. add attribute
        model.addAttribute("boardList", list);
        // 4. forward/redirect
        return "list";
    }

    @GetMapping("/id/{id}")
    public String board(@PathVariable("id") Integer id, Model model) {
        // 1. request param
        // 2. business logic
        Board board = service.getBoard(id);
        // 3. add attribute
        model.addAttribute("board", board);
        // 4. forward/redirect
        return "get";
    }

    @GetMapping("/update/{id}")
    public String update(@PathVariable("id") Integer id, Model model) {
        model.addAttribute("board", service.getBoard(id));
        return "update";
    }

    @PostMapping("/update/{id}")
    public String updateProcess(Board board, RedirectAttributes rttr) {

        boolean ok = service.update(board);

        if (ok) {
            // 해당 게시물 보기로 리디렉션
            rttr.addAttribute("success", "success");
            return "redirect:/id/" + board.getId();
        } else {
            // 수정 form으로 리디렉션
            rttr.addAttribute("fail", "fail");
            return "redirect:/update/" + board.getId();
        }

    }

    @PostMapping("/remove")
    public String remove(Integer id, RedirectAttributes rttr) {
        boolean ok = service.remove(id);

        if(ok) {
            rttr.addAttribute("success", "remove");
            return "redirect:/list";
        } else {
            rttr.addAttribute("fail", "fail");
            return "redirect:/id/" + id;
        }
    }
}
