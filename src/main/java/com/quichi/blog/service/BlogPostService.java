package com.quichi.blog.service;

import com.quichi.blog.models.BlogPost;
import com.quichi.blog.repositories.BlogPostRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class BlogPostService {
    @Autowired
    BlogPostRepository blogPostRepository;

    public boolean saveOrUpdate(BlogPost person) {
        blogPostRepository.save(person);
        return true;
    }
}
