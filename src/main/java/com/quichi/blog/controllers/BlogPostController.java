package com.quichi.blog.controllers;

import com.quichi.blog.models.BlogPost;
import com.quichi.blog.service.BlogPostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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

    @GetMapping(value = "/post/all")
    public ResponseEntity getBlog(){
        blogPostService.getAllPost();
        return  new ResponseEntity(blogPostService.getAllPost(), HttpStatus.OK);
    }

    @GetMapping(value = "/post/{id}")
    public ResponseEntity getBlogById(@PathVariable Integer id){
        return new ResponseEntity(blogPostService.getPostById(id), HttpStatus.OK);
    }

    @DeleteMapping(value = "/post/{id}")
    public ResponseEntity deletePostById(@PathVariable int id){
        blogPostService.deletePost(id);
        return new ResponseEntity(HttpStatus.OK);
    }

    @PutMapping(value = "/post")
    public ResponseEntity updatePost(@RequestBody BlogPost blogPost){
        HttpHeaders response = new HttpHeaders();
        response.add("Content-Location", "test");
        blogPostService.update(blogPost);
        return new ResponseEntity(HttpStatus.OK);
    }
}
