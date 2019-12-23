package com.quichi.blog.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;

@Configuration
@ComponentScan("com.quichi.blog")
@PropertySource("classpath:application.properties")
public class AppConfig {
    public AppConfig() {
    }

    @Autowired
    Environment environment;

//    private final String URL = environment.getProperty("spring.datasource.url");
//    private final String USER = environment.getProperty("spring.datasource.username");
//    private final String PASSWORD = environment.getProperty("spring.datasource.password");
//    private final String DRIVER = environment.getProperty("spring.datasource.driverClassName");
//
//    @Bean
//    public JdbcTemplate getJdbcTemplate() {
//        return new JdbcTemplate(dataSource());
//    }
//
//    @Bean
//    DataSource dataSource() {
//        DriverManagerDataSource driverManagerDataSource = new DriverManagerDataSource();
//        driverManagerDataSource.setUrl(environment.getProperty(URL));
//        driverManagerDataSource.setUsername(environment.getProperty(USER));
//        driverManagerDataSource.setPassword(environment.getProperty(PASSWORD));
//        driverManagerDataSource.setDriverClassName(environment.getProperty(DRIVER));
//        return driverManagerDataSource;
//    }
}
