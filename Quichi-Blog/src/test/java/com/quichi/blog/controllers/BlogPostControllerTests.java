package com.quichi.blog.controllers;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.quichi.blog.models.BlogPost;
import com.quichi.blog.models.exceptions.BlogPostDeletionException;
import com.quichi.blog.models.exceptions.BlogPostException;
import com.quichi.blog.models.exceptions.BlogPostInsertionException;
import com.quichi.blog.models.exceptions.BlogPostUpdateException;
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
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.doThrow;
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

    private BlogPost blogPost;

    @Autowired
    private ObjectMapper objectMapper;

    @BeforeEach
    void setUp() {

         blogPost = BlogPost.builder()
                .title("Test Title")
                .description("Test Description")
                .build();

        initMocks(this);
        this.mockMvc = standaloneSetup(blogPostController).build();
    }

    @Test
    void shouldReturn201Response() throws Exception {

        mockMvc.perform(post("http://localhost:8080/api/blog")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(blogPost)))
                .andExpect(status().is(201))
                .andExpect(MockMvcResultMatchers.header().exists("Content-Location"));
    }

    @Test
    void getAllShouldReturn200Response() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get("http://localhost:8080/api/blog")
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON));
    }

    @Test
    void createBlogPostShouldReturn500Response() throws Exception {
        doThrow(BlogPostInsertionException.class).when(blogPostService).insert(any(BlogPost.class));

        mockMvc.perform(MockMvcRequestBuilders.post("http://localhost:8080/api/blog")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(blogPost)))
                .andExpect(status().is5xxServerError());
    }

    @Test
    void deleteBlogPostShouldReturn500Response() throws Exception {
        doThrow(BlogPostDeletionException.class).when(blogPostService).delete(1);

        mockMvc.perform(MockMvcRequestBuilders.delete("http://localhost:8080/api/blog/1")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(blogPost)))
                .andExpect(status().is5xxServerError());
    }

    @Test
    void updateBlogPostShouldReturn500Response() throws Exception {
        doThrow(BlogPostUpdateException.class).when(blogPostService).update(any(BlogPost.class));

        mockMvc.perform(MockMvcRequestBuilders.put("http://localhost:8080/api/blog")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(blogPost)))
                .andExpect(status().is5xxServerError());
    }

    @Test
    void getBlogPostByIdShouldReturn500Response() throws Exception {
        doThrow(BlogPostException.class).when(blogPostService).getById(1);

        mockMvc.perform(MockMvcRequestBuilders.get("http://localhost:8080/api/blog/1")
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().is5xxServerError());
    }

    @Test
    void getAllBlogPostsShouldReturn500Response() throws Exception {
        doThrow(BlogPostException.class).when(blogPostService).getAll();

        mockMvc.perform(MockMvcRequestBuilders.get("http://localhost:8080/api/blog")
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().is5xxServerError());
    }

    @Test
    void shouldGetPostById() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get("http://localhost:8080/api/blog/1")
                  .accept(MediaType.APPLICATION_JSON))
                  .andExpect(status().isOk());
    }

    @Test
    void shouldDeletePostById() throws Exception {
        mockMvc.perform( MockMvcRequestBuilders
                .delete("http://localhost:8080/api/blog/1"))
                .andExpect(MockMvcResultMatchers.status().isOk());
    }

    @Test
    void shouldUpdatePostGivenAnPostBlog() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders
                .put("http://localhost:8080/api/blog")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(blogPost)))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.header().exists("Content-Location"));
    }
}
