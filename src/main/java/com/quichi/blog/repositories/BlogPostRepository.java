package com.quichi.blog.repositories;

import com.quichi.blog.models.BlogPost;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.Calendar;
import java.util.Date;

@Repository
public class BlogPostRepository{
    @Autowired
    JdbcTemplate jdbcTemplate;

    public boolean save(BlogPost blogPost) {
        Date today = Calendar.getInstance().getTime();
        jdbcTemplate.update("insert into BLOG_POST (id,created_date, description, title ) "+"values(?,?,?,?)",
             blogPost.getId(), today,  blogPost.getDescription(), blogPost.getTitle());
        return true;
    }
}
