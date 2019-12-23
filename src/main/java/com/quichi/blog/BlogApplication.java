package com.quichi.blog;

import com.quichi.blog.config.AppConfig;
import org.springframework.boot.Banner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.context.support.AnnotationConfigWebApplicationContext;

@SpringBootApplication
public class BlogApplication {



	public static void main(String[] args) {
		AnnotationConfigWebApplicationContext context = new AnnotationConfigWebApplicationContext();
		context.register(AppConfig.class);
		SpringApplication sa = new SpringApplication(BlogApplication.class);
		sa.setBannerMode(Banner.Mode.OFF);
		sa.run(args);
	}

}
