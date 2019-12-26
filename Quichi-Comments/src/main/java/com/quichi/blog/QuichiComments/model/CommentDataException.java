package com.quichi.blog.QuichiComments.model;

public class CommentDataException extends Exception {

    @Override
    public String getMessage() {
        return "The message could not be created";
    }
}
