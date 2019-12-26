package com.quichi.blog.QuichiComments.service;

import com.quichi.blog.QuichiComments.model.Comment;
import com.quichi.blog.QuichiComments.repository.CommentJdbcRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CommentService {

    @Autowired
    private CommentJdbcRepository commentJdbcRepository;

    public int save(Comment comment) {
        return commentJdbcRepository.save(comment);
    }

    public List<Comment> getAll() {
        return commentJdbcRepository.getAll();
    }

    public Comment findById(long id) throws ClassNotFoundException {
        return commentJdbcRepository.findById(id).orElseThrow(ClassNotFoundException::new);
    }
}
