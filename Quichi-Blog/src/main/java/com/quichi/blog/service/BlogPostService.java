package com.quichi.blog.service;

import com.quichi.blog.models.BlogPost;
import com.quichi.blog.models.Comment;
import com.quichi.blog.models.exceptions.BlogPostDeletionException;
import com.quichi.blog.models.exceptions.BlogPostException;
import com.quichi.blog.models.exceptions.BlogPostInsertionException;
import com.quichi.blog.models.exceptions.BlogPostUpdateException;
import com.quichi.blog.repositories.BlogPostRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;

import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;
import java.util.List;

@Service
public class BlogPostService {

    @Autowired
    BlogPostRepository blogPostRepository;

    private WebClient webClient;

    @Autowired
    public BlogPostService(String baseUrl) {
        this.webClient = WebClient.builder().baseUrl(baseUrl).defaultHeader(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE).build();
    }


    public List<BlogPost> getAll() throws BlogPostException {
        try {
            return blogPostRepository.getAll();
        } catch (Exception ex) {
            throw new BlogPostException();
        }
    }

    public BlogPost getById(int id) throws BlogPostException {

        try {
            BlogPost blogPost = blogPostRepository.getPostById(id);
            Mono<List<Comment>> comments = webClient
                    .get()
                    .uri("/comments", id)
                    .accept(MediaType.APPLICATION_JSON)
                    .retrieve()
                    .bodyToFlux(Comment.class).collectList();

            return BlogPost
                    .builder()
                    .id(blogPost.getId())
                    .title(blogPost.getTitle())
                    .description(blogPost.getDescription())
                    .createdDate(blogPost.getCreatedDate())
                    .comments(comments.block())
                    .build();

        } catch (Exception ex) {
            throw new BlogPostException();
        }
    }

    public int insert(BlogPost person) throws BlogPostInsertionException {
        int result = blogPostRepository.insert(person);
        if(result < 0) {
            throw new BlogPostInsertionException();
        } else {
            return result;
        }
    }

    public void delete(int id) throws BlogPostDeletionException {
        int result = blogPostRepository.delete(id);
        if (result == 0) {
            throw new BlogPostDeletionException();
        }
    }

    public void update(int id, BlogPost postUpdated) throws BlogPostUpdateException {
        int response = blogPostRepository.update(id, postUpdated);
        if (response == 0) {
            throw new BlogPostUpdateException();
        }
    }

    public Comment createComment(Comment comment) throws BlogPostException {
        try {
            Mono<Comment> commentMono = webClient
                    .post()
                    .uri("/comments")
                    .body(Mono.just(comment), Comment.class)
                    .accept(MediaType.APPLICATION_JSON)
                    .retrieve()
                    .bodyToMono(Comment.class);
                   commentMono.block();
            return  comment;
        } catch (Exception ex){
            throw new BlogPostException();
        }
    }
}
