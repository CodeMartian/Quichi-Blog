package com.quichi.blog.controllers;

import com.quichi.blog.models.BlogPost;
import com.quichi.blog.models.exceptions.BlogPostDeletionException;
import com.quichi.blog.models.exceptions.BlogPostInsertionException;
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
    public  ResponseEntity post(@RequestBody BlogPost blogPost) {
        HttpHeaders response = new HttpHeaders();
        response.add("Content-Location", "test");
        try {
            blogPostService.insert(blogPost);
        } catch (BlogPostInsertionException ex) {
            return new ResponseEntity(ex.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return new ResponseEntity(response, HttpStatus.CREATED);
    }

    @GetMapping(value = "/post/all")
    public ResponseEntity getBlog(){
        blogPostService.getAll();
        return  new ResponseEntity(blogPostService.getAll(), HttpStatus.OK);
    }

    @GetMapping(value = "/post/{id}")
    public ResponseEntity getBlogById(@PathVariable Integer id){
        return new ResponseEntity(blogPostService.getById(id), HttpStatus.OK);
    }

    @DeleteMapping(value = "/post/{id}")
    public ResponseEntity deletePostById(@PathVariable int id) {
        try {
            blogPostService.delete(id);
        } catch (BlogPostDeletionException ex) {
            return new ResponseEntity(ex.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
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
