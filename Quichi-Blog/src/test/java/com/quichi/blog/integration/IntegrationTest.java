package com.quichi.blog.integration;


import com.quichi.blog.models.BlogPost;
import com.quichi.blog.repositories.BlogPostRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;

@ExtendWith(SpringExtension.class)
@SpringBootTest
public class IntegrationTest {

    @Autowired
    private BlogPostRepository blogPostRepository;

    private BlogPost blogPost;

    @BeforeEach
    void setUp() {
    blogPost = BlogPost.builder()
            .title("Title ")
            .description("Description")
            .build();
    }

    @Test
    public void shouldReturnAnIdWhenABlogPostSave() {
        int result = blogPostRepository.insert(blogPost);

        assertThat(result, is(1));
    }

}
