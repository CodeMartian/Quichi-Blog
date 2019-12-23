package com.quichi.blog.repositories;


import org.junit.jupiter.api.BeforeEach;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.jdbc.core.JdbcTemplate;

import static org.mockito.MockitoAnnotations.initMocks;

public class BlogPostRepositoryTest {

    @MockBean
    private JdbcTemplate jdbcTemplate;

    @BeforeEach
    void setUp() {
        initMocks(this);
    }


//    @Test
//    void shouldSaveBlogPostInDatabaseWhenPostRequestIsMade() {
//        when(jdbcTemplate.update())
//        BlogPost blogPost = new BlogPost(6,"title", "desc", new Date());
//        BlogPostRepository repository = new BlogPostRepository();
//        assertTrue(repository.save(blogPost));
//    }
}
