package com.example.prj3.service;

import com.example.prj3.dto.Board;
import com.example.prj3.mapper.BoardMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class BoardService {

    @Autowired
    private BoardMapper mapper;

    public List<Board> listBoard(){
        List<Board> list = mapper.selectAll();

        return list;
    }

    public Board getBoard(Integer id) {
        return mapper.selectById(id);
    }
}
