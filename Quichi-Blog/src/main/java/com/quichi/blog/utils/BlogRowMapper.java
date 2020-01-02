package com.quichi.blog.utils;

import com.quichi.blog.models.BlogPost;
import org.springframework.jdbc.core.RowMapper;

import javax.swing.tree.TreePath;
import java.sql.ResultSet;
import java.sql.SQLException;

public class BlogRowMapper implements RowMapper<BlogPost> {

    @Override
    public BlogPost mapRow(ResultSet resultSet, int rowNum) throws SQLException {

        BlogPost blogPost = BlogPost
                .builder()
                .id(resultSet.getInt("id"))
                .title(resultSet.getString("title"))
                .description(resultSet.getString("description"))
                .createdDate(resultSet.getDate("created_date"))
                .build();

        return  blogPost;

    }


}
