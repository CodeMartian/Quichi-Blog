package com.quichi.blog.QuichiComments.model;

public class CommentDataException extends Exception {

    private String message;

    public CommentDataException(String message) {
        super(message);
    }

    @Override
    public String getMessage() {
        return message;
    }
}
