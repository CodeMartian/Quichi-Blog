package com.quichi.blog.models.exceptions;

public class BlogPostInsertionException extends BlogPostException {

    @Override
    public String getMessage() {
        return "Error has occurred during insertion please try again";
    }
}
