package com.quichi.blog.controllers;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.quichi.blog.models.BlogPost;
import com.quichi.blog.service.BlogPostService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.reactive.server.WebTestClient;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultMatcher;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import static org.mockito.Mockito.when;
import static org.mockito.MockitoAnnotations.initMocks;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.setup.MockMvcBuilders.standaloneSetup;


@ExtendWith(SpringExtension.class)
@WebMvcTest
public class BlogPostControllerTests  {

    private MockMvc mockMvc;

    @InjectMocks
    BlogPostController blogPostController;

    @MockBean
    BlogPostService blogPostService;

    @Autowired
    private ObjectMapper objectMapper;

    @BeforeEach
    void setUp() {
        initMocks(this);
        this.mockMvc = standaloneSetup(blogPostController).build();
    }

    @Test
    void shouldReturn201Response() throws Exception {

        BlogPost post = BlogPost.builder()
                .title("Test Title")
                .description("Test Description")
                .build();
        mockMvc.perform(post("http://localhost:8080/blog/posts")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(post)))
                .andExpect(status().is(201))
                .andExpect(MockMvcResultMatchers.header().exists("Content-Location"));
    }

    @Test
    void shouldReturn200Response() throws Exception {
        BlogPost post = BlogPost.builder()
                .title("Test Title")
                .description("Test Description")
                .build();
        when(blogPostService.saveOrUpdate(post)).thenReturn(true);
        mockMvc.perform(MockMvcRequestBuilders.get("http://localhost:8080/blog/post/all")
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON));
    }

    @Test
    void shouldGetPostById() throws Exception {
        BlogPost post = BlogPost.builder()
                .id(1)
                .title("Test Title")
                .description("Test Description")
                .build();
        when(blogPostService.saveOrUpdate(post)).thenReturn(true);
        mockMvc.perform(MockMvcRequestBuilders.get("http://localhost:8080/blog/post/1")
                  .accept(MediaType.APPLICATION_JSON))
                  .andExpect(status().isOk());
    }

    @Test
    void shouldDeletePostById() throws Exception {
        BlogPost post = BlogPost.builder()
                .id(1)
                .title("Test Title")
                .description("Test Description")
                .build();
        when(blogPostService.saveOrUpdate(post)).thenReturn(true);
        mockMvc.perform( MockMvcRequestBuilders
                .delete("http://localhost:8080/blog/post/1"))
                .andExpect(MockMvcResultMatchers.status().isOk());
    }

    @Test
    void shouldUpdatePostGivenAnPostBlog() throws Exception {
        BlogPost post = BlogPost.builder()
                .id(1)
                .title("Test Title")
                .description("Test Description")
                .build();
        blogPostService.saveOrUpdate(post);
        mockMvc.perform(MockMvcRequestBuilders
                .put("http://localhost:8080/blog/post")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(post)))
                .andExpect(MockMvcResultMatchers.status().isOk());
    }
}
