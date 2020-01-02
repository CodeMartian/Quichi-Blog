package com.quichi.blog.services;

import com.quichi.blog.models.BlogPost;
import com.quichi.blog.repositories.BlogPostRepository;
import com.quichi.blog.service.BlogPostService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.mockito.MockitoAnnotations.initMocks;

public class BlogPostServiceTests {

    @Mock
    BlogPostRepository blogPostRepo;

    @InjectMocks
    BlogPostService blogPostService;

    @BeforeEach
    void setUp() {
        initMocks(this);
    }

    @Test
    void shouldSavePostWhenSaveOrUpdateIsCalled() {
        BlogPost blogPost = new BlogPost();
        when(blogPostRepo.save(blogPost)).thenReturn(true);
    }

    @Test
    void shouldGetAllPost(){
        blogPostService.getAllPost();
        verify(blogPostRepo).getAll();
    }

    @Test
    void shouldGetPostById(){
        int idPost = 2;
        BlogPost post = BlogPost.builder()
                .id(idPost)
                .title("Test Title")
                .description("Test Description")
                .build();
        when(blogPostRepo.save(post)).thenReturn(true);
        blogPostService.getPostById(idPost);
        verify(blogPostRepo).getPostById(idPost);
    }

    @Test
    void  shouldDeletePostById(){
        int idPost = 2;
        BlogPost post = BlogPost.builder()
                .id(idPost)
                .title("Test Title")
                .description("Test Description")
                .build();
        blogPostRepo.save(post);
        when(blogPostRepo.deletePost(idPost)).thenReturn(idPost);
    }

    @Test
    void shouldUpdatePostGivenAnID(){
        int idPost = 2;
        BlogPost post = BlogPost.builder()
                .id(idPost)
                .title("Test Title")
                .description("Test Description")
                .build();
        blogPostRepo.save(post);
        BlogPost postUpdated = BlogPost.builder()
                .id(idPost)
                .title("Test Title")
                .description("Test Description")
                .build();
        when(blogPostService.update(postUpdated));
    }


}
