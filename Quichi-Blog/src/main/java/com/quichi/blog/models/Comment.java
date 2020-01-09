package com.quichi.blog.models;

import java.util.Date;

public class Comment {

    private long id;
    private String content;
    private Date createdDate;
    private long postId;

    public Comment(){}

    public Comment(
            final String content,
            final Date createdDate,
            final long postId) {

        this.content = content;
        this.createdDate = createdDate;
        this.postId = postId;

    }

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

    public long getPostId() {
        return postId;
    }
}