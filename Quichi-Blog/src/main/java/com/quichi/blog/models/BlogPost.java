package com.quichi.blog.models;

import lombok.*;

import java.util.Date;
import java.util.List;

@Builder
@NoArgsConstructor
@AllArgsConstructor
public class BlogPost {

    private int id;
    private String title;
    private String description;

    private List<Comment> comments;
    private Date createdDate;


    public int getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public List<Comment> getComments() {
        return comments;
    }

    public Date getCreatedDate() {
        return createdDate;
    }

    public String getDescription() {
        return description;
    }

    public void setComments(List<Comment> comments) {
        this.comments = comments;
    }
}
