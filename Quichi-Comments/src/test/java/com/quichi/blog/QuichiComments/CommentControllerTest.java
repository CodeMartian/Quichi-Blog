package com.quichi.blog.QuichiComments;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.quichi.blog.QuichiComments.controller.CommentController;
import com.quichi.blog.QuichiComments.model.Comment;
import com.quichi.blog.QuichiComments.service.CommentService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentMatchers;
import org.mockito.InjectMocks;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest
public class CommentControllerTest {

    MockMvc mockMvc;

    @MockBean
    CommentService commentService;

    @InjectMocks
    CommentController commentControllerTest;

    @BeforeEach
    public void setup() {

        MockitoAnnotations.initMocks(this);

        this.mockMvc = MockMvcBuilders.standaloneSetup(commentControllerTest).build();
    }

   @Test
    public void shouldReturn201WhenCommentCreated() throws Exception {

       Mockito.when(commentService.save(ArgumentMatchers.isA(Comment.class))).thenReturn(1);

        mockMvc.perform(MockMvcRequestBuilders
                .post("/comments")
                .contentType(MediaType.APPLICATION_JSON)
                .content(asJsonString(new Comment(1, "Firt Comment")))
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isCreated())
                .andExpect(MockMvcResultMatchers.jsonPath("$.id").exists());
    }

    private static String asJsonString(final Object obj) {
        try {
            return new ObjectMapper().writeValueAsString(obj);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
