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
            SELECT b.id, b.title, b.body, b.writer, b.inserted, bf.fileName
            FROM board b
                LEFT JOIN board.boardfile bf
                    on b.id = bf.boardId
            WHERE b.id = #{id}
            """)
    @ResultMap("boardResultMap")
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
            SELECT b.id, b.title, b.writer, b.inserted, COUNT(bf.id) `fileCount`
            FROM board b
                LEFT JOIN boardfile bf
                    ON b.id = bf.boardId
            <where>
                <if test="category eq 'all' or category eq 'title'">
                    title LIKE #{pattern}
                </if>
                <if test="category eq 'all' or category eq 'body'">
                    OR body LIKE #{pattern}
                </if>
                <if test="category eq 'all' or category eq 'writer'">
                    OR writer LIKE #{pattern}
                </if>
            </where>
            GROUP BY b.id
            ORDER BY b.id DESC
            LIMIT #{startIndex}, #{rowPerPage}
            </script>
            """)
    List<Board> selectAllPaging(Integer startIndex, int rowPerPage, String search, String category);

    @Select("""
            <script>
            <bind name="pattern" value = "'%' + search + '%'" />
            SELECT COUNT(*)
            FROM board
            <where>
                <if test="category eq 'all' or category eq 'title'">
                    title LIKE #{pattern}
                </if>
                <if test="category eq 'all' or category eq 'body'">
                    OR body LIKE #{pattern}
                </if>
                <if test="category eq 'all' or category eq 'writer'">
                    OR writer LIKE #{pattern}
                </if>
            </where>
            ORDER BY id DESC
            </script>
            """)
    Integer countAll(String search, String category);

    @Insert("""
            INSERT INTO boardfile (boardId, fileName)
            VALUES (#{boardId}, #{fileName})
            """)
    Integer insertFile(Integer boardId, String fileName);

}
