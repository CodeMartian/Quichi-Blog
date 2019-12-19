package com.quichi.blog.repositories;

import com.quichi.blog.models.BlogPost;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

@Repository
public class BlogPostRepository {
    @Autowired
    JdbcTemplate jdbcTemplate;

    public boolean save(BlogPost blogPost) {
        jdbcTemplate.update("insert into BLOG_POST (title, description, createdDate) "+"values(?,?,?)",
                new Object[] { blogPost.getTitle(), blogPost.getDescription(), blogPost.getCreatedDate() });
        return false;
    }
}
