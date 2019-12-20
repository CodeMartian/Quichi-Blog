package com.quichi.blog.repositories;


import com.quichi.blog.models.BlogPost;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.jdbc.core.JdbcTemplate;

import java.util.Date;

import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.MockitoAnnotations.initMocks;

public class BlogPostRepositoryTest {

    @MockBean
    private JdbcTemplate jdbcTemplate = new JdbcTemplate();

    @BeforeEach
    void setUp() {
        initMocks(this);
    }

    @Test
    void shouldSaveBlogPostInDatabaseWhenPostRequestIsMade() {
        BlogPost blogPost = new BlogPost("title", "desc", new Date());
        BlogPostRepository repository = new BlogPostRepository();
        assertTrue(repository.save(blogPost));
    }
}
