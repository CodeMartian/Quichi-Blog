package com.quichi.blog.QuichiComments.model;

public class Comment {

    private long id;
    private String content;

    public Comment(
            final long id,
            final String content) {

        this.id = id;
        this.content = content;
    }

    public long getId() {
        return id;
    }
}
