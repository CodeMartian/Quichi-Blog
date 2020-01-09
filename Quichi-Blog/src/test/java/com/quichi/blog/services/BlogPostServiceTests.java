package com.quichi.blog.services;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.quichi.blog.models.BlogPost;
import com.quichi.blog.models.Comment;
import com.quichi.blog.models.exceptions.BlogPostException;
import com.quichi.blog.models.exceptions.BlogPostInsertionException;
import com.quichi.blog.repositories.BlogPostRepository;
import com.quichi.blog.service.BlogPostService;
import okhttp3.mockwebserver.MockResponse;
import okhttp3.mockwebserver.MockWebServer;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import java.util.Date;

import static org.hamcrest.Matchers.*;
import static org.junit.Assert.assertThat;
import static org.mockito.Mockito.when;
import static org.mockito.MockitoAnnotations.initMocks;

@ExtendWith(MockitoExtension.class)
public class BlogPostServiceTests {

    @Mock
    BlogPostRepository blogPostRepo;

    private final MockWebServer mockWebServer = new MockWebServer();

    private BlogPost blogPost;

    @InjectMocks
    BlogPostService blogPostService = new BlogPostService(mockWebServer.url("http://localhost:8081/").toString());

    @Autowired
    private ObjectMapper objectMapper;

    @BeforeEach
    void setUp() {
        initMocks(this);
         blogPost = BlogPost.builder()
                .id(1)
                .title("Test Title")
                .description("Test Description")
                .build();
    }

    @AfterEach
    void tearDown() throws Exception {
        mockWebServer.shutdown();
    }

    @Test
    void givenABlogPostIdShouldReturnTheMockedComment() throws BlogPostException {
        when(blogPostRepo.getPostById(1)).thenReturn(BlogPost.builder().id(1).build());
        mockWebServer.enqueue(new MockResponse()
                .setResponseCode(HttpStatus.OK.value())
                .setHeader(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE)
                .setBody("{\"id\" : 0, \"content\" : \"Test comment\", \"createdDate\" : null}"));

        BlogPost response = blogPostService.getById(1);
        assertThat(response.getId(), is(1));
        assertThat(response.getComments(), is(not(nullValue())));
    }


    @Test
    void shouldCreateCommentGivenBlogPostIdAndComment() throws Exception {
        Comment comment = new Comment("Test comment",new Date(), 1);
       mockWebServer.enqueue(new MockResponse()
                .setResponseCode(HttpStatus.CREATED.value())
                .setHeader(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_CBOR_VALUE)
                .setBody("{\"id\" : 0, \"content\" : \"Test comment\", \"createdDate\" : null, \"postId\" : 1}")
                .addHeader("Content-Type", "application/json"));
       Comment commentResult = blogPostService.createComment(comment);
       assertThat(commentResult, is(comment));

    }



}
