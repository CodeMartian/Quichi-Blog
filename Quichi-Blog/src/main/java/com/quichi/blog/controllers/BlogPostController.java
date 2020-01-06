package com.quichi.blog.controllers;

import com.quichi.blog.models.BlogPost;
import com.quichi.blog.models.exceptions.BlogPostDeletionException;
import com.quichi.blog.models.exceptions.BlogPostException;
import com.quichi.blog.models.exceptions.BlogPostInsertionException;
import com.quichi.blog.models.exceptions.BlogPostUpdateException;
import com.quichi.blog.service.BlogPostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api")
public class BlogPostController {

    @Autowired
    BlogPostService blogPostService;

    @PostMapping(value = "/blog")
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

    @GetMapping(value = "/blog")
    public ResponseEntity getBlog() {
        try {
            return new ResponseEntity(blogPostService.getAll(), HttpStatus.OK);
        } catch (BlogPostException ex) {
            return new ResponseEntity(ex.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping(value = "/blog/{id}")
    public ResponseEntity getBlogById(@PathVariable Integer id) {
        try {
            return new ResponseEntity(blogPostService.getById(id), HttpStatus.OK);
        } catch (BlogPostException ex) {
            return new ResponseEntity(ex.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @DeleteMapping(value = "/blog/{id}")
    public ResponseEntity deletePostById(@PathVariable int id) {
        try {
            blogPostService.delete(id);
        } catch (BlogPostDeletionException ex) {
            return new ResponseEntity(ex.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return new ResponseEntity(HttpStatus.OK);
    }

    //TODO: include an id in the url
    @PutMapping(value = "/blog")
    public ResponseEntity updatePost(@RequestBody BlogPost blogPost){
        HttpHeaders response = new HttpHeaders();
        response.add("Content-Location", "test");
        try {
            blogPostService.update(blogPost);
        } catch(BlogPostUpdateException ex) {
            return new ResponseEntity(ex.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return new ResponseEntity(response, HttpStatus.OK);
    }
}
