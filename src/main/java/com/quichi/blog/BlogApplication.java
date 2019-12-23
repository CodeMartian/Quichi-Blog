package com.quichi.blog;

import org.springframework.boot.Banner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class BlogApplication {



	public static void main(String[] args) {
		SpringApplication sa = new SpringApplication(BlogApplication.class);
		sa.setBannerMode(Banner.Mode.OFF);
		sa.run(args);
	}

}
