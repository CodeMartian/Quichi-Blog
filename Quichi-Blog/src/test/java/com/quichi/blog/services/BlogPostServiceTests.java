package com.quichi.blog.services;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.quichi.blog.models.BlogPost;
import com.quichi.blog.models.Comment;
import com.quichi.blog.repositories.BlogPostRepository;
import com.quichi.blog.service.BlogPostService;
import okhttp3.mockwebserver.MockResponse;
import okhttp3.mockwebserver.MockWebServer;
import org.hamcrest.Matchers;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;

import java.io.IOException;

import static org.hamcrest.Matchers.*;
import static org.junit.Assert.assertThat;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.mockito.MockitoAnnotations.initMocks;

public class BlogPostServiceTests {

    @Mock
    BlogPostRepository blogPostRepo;

    private final MockWebServer mockWebServer = new MockWebServer();

    @InjectMocks
    BlogPostService blogPostService = new BlogPostService(mockWebServer.url("").toString());

    @BeforeEach
    void setUp() {
        initMocks(this);
    }

    @AfterEach
    void tearDown() throws Exception {
        mockWebServer.shutdown();
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

    @Test
    void givenABlogPostIdShouldReturnTheMockedComment() {
        when(blogPostRepo.getPostById(1)).thenReturn(BlogPost.builder().id(1).build());
        mockWebServer.enqueue(new MockResponse()
                .setResponseCode(HttpStatus.OK.value())
                .setHeader(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE)
                .setBody("{\"id\" : 0, \"content\" : \"Test comment\", \"createdDate\" : null}"));
        BlogPost response = blogPostService.getPostById(1);
        assertThat(response.getId(), is(1));
        assertThat(response.getComments(), is(not(nullValue())));
    }
}
