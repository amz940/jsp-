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
import org.springframework.web.bind.annotation.RequestMapping;

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
}
