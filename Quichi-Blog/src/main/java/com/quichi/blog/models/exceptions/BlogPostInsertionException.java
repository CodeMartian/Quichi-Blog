package com.quichi.blog.models.exceptions;

public class BlogPostInsertionException extends Exception {

    @Override
    public String getMessage() {
        return "Error has occurred during insertion please try again";
    }
}
