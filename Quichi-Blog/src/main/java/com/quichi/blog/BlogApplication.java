package com.quichi.blog;

import com.quichi.blog.service.BlogPostService;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Scope;

@SpringBootApplication
public class BlogApplication {

	@Bean
	public String BLOG_COMMENTS_URL() {
		return "c";
	}

/*	@Bean
	@Scope(value = "prototype")
	public BlogPostService getService() {
		return new BlogPostService(BLOG_COMMENTS_URL);
	}*/

	public static void main(String[] args) {
		SpringApplication.run(BlogApplication.class, args);
	}

}
