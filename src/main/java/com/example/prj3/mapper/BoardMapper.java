package com.example.prj3.mapper;

import com.example.prj3.dto.Board;
import org.apache.ibatis.annotations.*;

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

    @Insert("""
            INSERT INTO board (title, body, writer)
            VALUES (
                #{title},
                #{body},
                #{writer}
            )
            """)
    @Options(useGeneratedKeys = true, keyProperty = "id")
    Integer insert(Board board);

    @Select("""
            <script>
            <bind name="pattern" value = "'%' + search + '%'" />
            SELECT id, title, board.writer, board.inserted
            FROM board
            WHERE
                title LIKE #{pattern}
                OR body LIKE #{pattern}
                OR writer LIKE #{pattern}
            ORDER BY id DESC
            LIMIT #{startIndex}, #{rowPerPage}
            </script>
            """)
    List<Board> selectAllPaging(Integer startIndex, int rowPerPage, String search);

    @Select("""
            <script>
            <bind name="pattern" value = "'%' + search + '%'" />
            SELECT COUNT(*)
            FROM board
            WHERE
                title LIKE #{pattern}
                OR body LIKE #{pattern}
                OR writer LIKE #{pattern}
            ORDER BY id DESC
            </script>
            """)
    Integer countAll(String search);

}
