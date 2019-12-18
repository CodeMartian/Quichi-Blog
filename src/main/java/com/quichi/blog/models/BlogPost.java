package com.quichi.blog.models;

import java.util.Date;

public class BlogPost {

    private String title;
    private String description;
    private Date createdDate;

    public BlogPost(String title, String description, Date createdDate) {
        this.title = title;
        this.description = description;
        this.createdDate = createdDate;
    }

    public String getTitle() {
        return title;
    }
    public Date getCreatedDate() {
        return createdDate;
    }

    public String getDescription() {
        return description;
    }
}
