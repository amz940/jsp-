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

    public List<Board> listBoard(Integer page) {
        Integer startIndex = (page - 1) * 10;
        // 게시물 정보
        return mapper.selectAllPaging(startIndex);
        // 페이징
    }
}
