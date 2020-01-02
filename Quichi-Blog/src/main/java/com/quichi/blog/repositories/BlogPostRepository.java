package com.quichi.blog.repositories;

import com.quichi.blog.models.BlogPost;
import com.quichi.blog.utils.BlogRowMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.Calendar;
import java.util.Date;
import java.util.List;

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

    public List<BlogPost> getAll() {
        return jdbcTemplate.query("select * from BLOG_POST", new BlogRowMapper());
    }

    public BlogPost getPostById(int id) {
        return jdbcTemplate.queryForObject("select * from BLOG_POST where id = ?",
                new Object[]{id},new BlogRowMapper());
    }

    public int deletePost(int idPost) {
        return jdbcTemplate.update("delete from BLOG_POST where id = ?", idPost);
    }

    public int updatePost(BlogPost postUpdated) {
        return jdbcTemplate.update("update BLOG_POST set description = ?, title =? where id = ?",
                postUpdated.getDescription(),postUpdated.getTitle(), postUpdated.getId());
    }
}
