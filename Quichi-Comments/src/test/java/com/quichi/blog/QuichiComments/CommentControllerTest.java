package com.quichi.blog.QuichiComments;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.quichi.blog.QuichiComments.model.Comment;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.reactive.server.WebTestClient;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest
public class CommentControllerTest {

    @Autowired
    MockMvc mockMvc;

   @Test
    public void shouldReturn201WhenCommentCreated() throws Exception {
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
