package com.quichi.blog.services;

import com.quichi.blog.models.BlogPost;
import com.quichi.blog.repositories.BlogPostRepository;
import com.quichi.blog.service.BlogPostService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;

import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.when;
import static org.mockito.MockitoAnnotations.initMocks;

public class BlogPostServiceTests {

    @Mock
    BlogPostRepository blogPostRepo;

    @InjectMocks
    BlogPostService blogPostService = new BlogPostService();

    @BeforeEach
    void setUp() {
        initMocks(this);
    }

    @Test
    void shouldReturnTrueWhenSaveOrUpdateIsCalled() {
        //Given
        BlogPost blogPost = new BlogPost();
        when(blogPostRepo.save(blogPost)).thenReturn(true);

        //When
        //Then
        assertTrue(blogPostService.saveOrUpdate(blogPost));
    }
}
