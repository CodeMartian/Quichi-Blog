package com.quichi.blog.QuichiComments.service;

import com.quichi.blog.QuichiComments.model.Comment;
import com.quichi.blog.QuichiComments.repository.CommentJdbcRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

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

    public int delete(long id) {
       return commentJdbcRepository.delete(id);
    }

    public int update(Comment comment) {
         return commentJdbcRepository.update(comment);
    }

    public List<Comment> getCommentsByPostId(long blogPostId) {
        return commentJdbcRepository.getCommentsByPostId(blogPostId);
    }
}
