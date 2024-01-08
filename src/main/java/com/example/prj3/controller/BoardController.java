package com.example.prj3.controller;

import com.example.prj3.dto.Board;
import com.example.prj3.mapper.BoardMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequestMapping("/")
public class BoardController {

    @Autowired
    private BoardMapper mapper;
    // 게시물 목록
    @GetMapping("list")
    public String list(Model model) {
        // 1. request param  수집/가공
        // 2. business logic 처리
        List<Board> list = mapper.selectAll();
        // 3. add attribute
        model.addAttribute("boardList", list);
        // 4. forward/redirect
        return "list";
    }
}
