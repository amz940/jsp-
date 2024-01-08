package com.example.prj3.mapper;

import com.example.prj3.dto.Board;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import java.util.List;

@Mapper
public interface BoardMapper {
    @Select("""
            SELECT * FROM board
            ORDER BY id DESC;
            """)
    List<Board> selectAll();

    @Select("""
            SELECT * FROM board
            WHERE id = #{id}
            """)
    Board selectById(Integer id);

    @Update("""
            UPDATE board
            SET title = #{title},
                body = #{body},
                writer = #{writer}
            WHERE id = #{id}
            """)
    Integer update(Board board);

    @Delete("""
            DELETE FROM board
            WHERE id = #{id}
            """)
    Integer remove(Integer id);
}
