package com.quichi.blog.models.exceptions;

public class BlogPostUpdateException extends BlogPostException {
    @Override
    public String getMessage() {
        return "An error occurred while trying to update please try again";
    }
}
