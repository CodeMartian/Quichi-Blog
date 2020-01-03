package com.quichi.blog.service;

import com.quichi.blog.models.BlogPost;
import com.quichi.blog.models.Comment;
import com.quichi.blog.repositories.BlogPostRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

import java.util.ArrayList;
import java.util.List;

@Service
public class BlogPostService {
    @Autowired
    BlogPostRepository blogPostRepository;

    private WebClient webClient;
    private String baseUrl;

    public BlogPostService() {
        this.baseUrl = "http://localhost:8080";
        this.webClient = WebClient.builder().baseUrl(baseUrl).defaultHeader(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE).build();
    }

    public BlogPostService(String baseUrl) {
        this.baseUrl = baseUrl;
        this.webClient = WebClient.builder().baseUrl(baseUrl).defaultHeader(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE).build();
    }

    public boolean saveOrUpdate(BlogPost person) {
        blogPostRepository.save(person);
        return true;
    }

    public List<BlogPost> getAllPost() {
          return blogPostRepository.getAll();
    }

    public BlogPost getPostById(int id) {
        BlogPost blogPost = blogPostRepository.getPostById(id);

        Mono<List<Comment>> comments = webClient
                .get()
                .uri("/comments", id)
                .accept(MediaType.APPLICATION_JSON)
                .retrieve()
                .bodyToFlux(Comment.class).collectList();

        blogPost.setComments(comments.block());
        return blogPost;
    }

    public boolean deletePost(int idPost) {
        blogPostRepository.deletePost(idPost);
        return true;
    }

    public boolean update(BlogPost postUpdated) {
        blogPostRepository.updatePost(postUpdated);
        return true;
    }
}
