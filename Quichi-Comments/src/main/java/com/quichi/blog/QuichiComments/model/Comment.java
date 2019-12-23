package com.quichi.blog.QuichiComments.model;

import java.util.Date;

public class Comment {

    private long id;
    private String content;

    private Date createdDate;

    public Comment(
            final long id,
            final String content,
            final Date createdDate) {

        this.id = id;
        this.content = content;
        this.createdDate = createdDate;
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
