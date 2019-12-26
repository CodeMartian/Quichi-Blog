package com.quichi.blog.QuichiComments.repository;

import com.quichi.blog.QuichiComments.model.Comment;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.Date;

@Repository
public class CommentJdbcRepository {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    public int save(Comment comment) {

        return jdbcTemplate.update("INSERT INTO comment values(?,?,?)",
                comment.getId(), comment.getContent(), new Date());
    }
}
