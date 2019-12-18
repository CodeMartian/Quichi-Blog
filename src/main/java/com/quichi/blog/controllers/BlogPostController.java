package com.quichi.blog.controllers;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/blog")
public class BlogPostController {

    @PostMapping(value = "/posts")
    public ResponseEntity post(){
        return new ResponseEntity(null, HttpStatus.CREATED);
    }
}
