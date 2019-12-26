package com.quichi.blog.QuichiComments.service;

import com.quichi.blog.QuichiComments.model.Comment;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.quichi.blog.QuichiComments.repository.CommentJdbcRepository;

@Service
public class CommentService {

    @Autowired
    private CommentJdbcRepository commentJdbcRepository;

    public int save(Comment comment) {
        return commentJdbcRepository.save(comment);
    }
}
