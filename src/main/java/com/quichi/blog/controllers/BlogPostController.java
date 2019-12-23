package com.quichi.blog.controllers;

import com.quichi.blog.models.BlogPost;
import com.quichi.blog.service.BlogPostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/blog")
public class BlogPostController {

    @Autowired
    BlogPostService blogPostService;

    @PostMapping(value = "/posts")
    public ResponseEntity post(@RequestBody BlogPost blogPost){
        HttpHeaders response = new HttpHeaders();
        response.add("Content-Location", "test");
        blogPostService.saveOrUpdate(blogPost);
        return new ResponseEntity(response, HttpStatus.CREATED);
    }
}
