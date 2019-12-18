package com.quichi.blog;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.quichi.blog.models.BlogPost;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.ResultMatcher;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import java.util.Date;

@WebMvcTest
@ExtendWith(SpringExtension.class)
public class BlogPostControllerTests {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @Test
    void shouldReturn201Response() throws Exception {

        BlogPost post = new BlogPost("New", "This a new", new Date());
        mockMvc.perform(MockMvcRequestBuilders.post("http://localhost:8080/blog/posts")
                .content(objectMapper.writeValueAsString(post)))
                .andExpect(MockMvcResultMatchers.status().is(201))
                .andExpect(MockMvcResultMatchers.header().exists("Content-Location"));

    }
}
