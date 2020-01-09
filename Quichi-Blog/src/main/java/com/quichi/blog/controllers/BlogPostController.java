package com.quichi.blog.controllers;

import com.quichi.blog.models.BlogPost;
import com.quichi.blog.models.Comment;
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
        try {
            int id = blogPostService.insert(blogPost);
            response.add("Content-Location", "/api/blog/" + id);
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

    @PutMapping(value = "/blog/{id}")
    public ResponseEntity updatePost(@PathVariable int id, @RequestBody BlogPost blogPost){
        HttpHeaders response = new HttpHeaders();
        response.add("Content-Location", "/api/blog/"+id);
        try {
            blogPostService.update(id, blogPost);
        } catch(BlogPostUpdateException ex) {
            return new ResponseEntity(ex.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return new ResponseEntity(response, HttpStatus.OK);
    }

    @PostMapping(value = "/blog/{id}/comment")
    public ResponseEntity createComment(@PathVariable int id, @RequestBody Comment comment){
        HttpHeaders response  = new HttpHeaders();
        response.add("Content-Location", "/api/blog/"+id+"/comment");
        try{
           blogPostService.createComment(comment);
        }catch (Exception ex){
            return new ResponseEntity(ex.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
       return new ResponseEntity(response, HttpStatus.CREATED);
    }
}
