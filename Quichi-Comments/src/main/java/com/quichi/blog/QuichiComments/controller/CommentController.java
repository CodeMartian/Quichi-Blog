package com.quichi.blog.QuichiComments.controller;

import com.quichi.blog.QuichiComments.model.Comment;
import com.quichi.blog.QuichiComments.service.CommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("comments")
public class CommentController {

   //@Autowired
   //private CommentService commentService;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Comment create(@RequestBody @Valid final Comment comment) {
        return new Comment(comment.getId(), comment.getContent());  //commentService.save(comment);
    }

}
