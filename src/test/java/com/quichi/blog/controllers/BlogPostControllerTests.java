package com.quichi.blog.controllers;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.quichi.blog.BlogApplication;
import com.quichi.blog.models.BlogPost;
import com.quichi.blog.service.BlogPostService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import java.util.Date;

import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.mockito.MockitoAnnotations.initMocks;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
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

        BlogPost post = BlogPost
                .builder()
                .title("Test Title")
                .description("Test Description")
                .build();

        mockMvc.perform(post("http://localhost:8080/blog/posts")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(post)))
                .andExpect(MockMvcResultMatchers.status().is(201))
                .andExpect(MockMvcResultMatchers.header().exists("Content-Location"));
    }
}
