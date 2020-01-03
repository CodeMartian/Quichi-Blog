package com.quichi.blog.models.exceptions;

public class BlogPostDeletionException extends Exception {
    @Override
    public String getMessage() {
        return "Something went wrong during deletion please try again";
    }
}
