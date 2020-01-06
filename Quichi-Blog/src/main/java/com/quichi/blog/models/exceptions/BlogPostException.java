package com.quichi.blog.models.exceptions;

public class BlogPostException extends Exception {
    @Override
    public String getMessage() {
        return "Oops! Something went wrong. Please try again.";
    }
}
