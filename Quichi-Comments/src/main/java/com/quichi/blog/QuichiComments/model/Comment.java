package com.quichi.blog.QuichiComments.model;

import java.util.Date;

public class Comment {

    private long id;
    private String content;

    private Date createdDate;

    public Comment(){}

    public Comment(
            final long id,
            final String content) {

        this.id = id;
        this.content = content;
        this.createdDate = new Date();
    }

    public long getId() {
        return id;
    }

    public Date getCreatedDate() {
        return createdDate;
    }

    public String getContent() {
        return content;
    }
}
