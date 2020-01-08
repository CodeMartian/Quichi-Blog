package com.quichi.blog.repositories;

import com.quichi.blog.models.BlogPost;
import com.quichi.blog.utils.BlogRowMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.Statement;
import java.util.Calendar;
import java.util.List;

@Repository
public class BlogPostRepository {

    @Autowired
    JdbcTemplate jdbcTemplate;

    public int insert(BlogPost blogPost) {
        KeyHolder keyHolder = new GeneratedKeyHolder();

        jdbcTemplate.update((connection) -> {
            PreparedStatement ps = connection.prepareStatement("insert into BLOG_POST (created_date, description, title ) values(?,?,?)", Statement.RETURN_GENERATED_KEYS);
            ps.setDate(1, new Date(Calendar.getInstance().getTime().getTime()));
            ps.setString(2, blogPost.getDescription());
            ps.setString(3, blogPost.getTitle());

            return ps;
        }, keyHolder);

        return keyHolder.getKey().intValue();
    }

    public List<BlogPost> getAll() {
        return jdbcTemplate.query("select * from BLOG_POST", new BlogRowMapper());
    }

    public BlogPost getPostById(int id) {
        return jdbcTemplate.queryForObject("select * from BLOG_POST where id = ?",
                new Object[]{id}, new BlogRowMapper());
    }

    public int delete(int id) {
        return jdbcTemplate.update("delete from BLOG_POST where id = ?", id);
    }

    public int update(int id, BlogPost postUpdated) {
        return jdbcTemplate.update("update BLOG_POST set description = ?, title =? where id = ?",
                postUpdated.getDescription(),postUpdated.getTitle(), id);
    }
}
