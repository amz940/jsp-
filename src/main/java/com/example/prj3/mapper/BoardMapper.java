package com.example.prj3.mapper;

import com.example.prj3.dto.Board;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface BoardMapper {
    @Select("""
            SELECT id, title, body, writer, inserted FROM board
            ORDER BY id DESC;
            """)
    List<Board> selectAll();
}
