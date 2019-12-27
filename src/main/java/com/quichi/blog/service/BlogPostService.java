package com.quichi.blog.service;

import com.quichi.blog.models.BlogPost;
import com.quichi.blog.repositories.BlogPostRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BlogPostService {
    @Autowired
    BlogPostRepository blogPostRepository;

    public boolean saveOrUpdate(BlogPost person) {
        blogPostRepository.save(person);
        return true;
    }

    public List<BlogPost> getAllPost() {
          return blogPostRepository.getAll();
    }

    public BlogPost getPostById(int id) {
       return blogPostRepository.getPostById(id);
    }
}
