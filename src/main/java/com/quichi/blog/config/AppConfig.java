package com.quichi.blog.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.datasource.DriverManagerDataSource;

import javax.annotation.Resource;
import javax.sql.DataSource;

@Configuration
@ComponentScan("com.quichi.blog")
@PropertySource("classpath:application.properties")
public class AppConfig {

    @Resource
    Environment environment;

    private final String URL = environment.getProperty("spring.datasource.url");
    private final String USER = environment.getProperty("spring.datasource.username");
    private final String PASSWORD = environment.getProperty("spring.datasource.password");
    private final String DRIVER = environment.getProperty("spring.datasource.driverClassName");

    @Bean
    public JdbcTemplate getJdbcTemplate() {
        return new JdbcTemplate(dataSource());
    }

    @Bean
    DataSource dataSource() {
        DriverManagerDataSource driverManagerDataSource = new DriverManagerDataSource();
        driverManagerDataSource.setUrl(URL);
        driverManagerDataSource.setUsername(USER);
        driverManagerDataSource.setPassword(PASSWORD);
        driverManagerDataSource.setDriverClassName(DRIVER);
        return driverManagerDataSource;
    }
}
