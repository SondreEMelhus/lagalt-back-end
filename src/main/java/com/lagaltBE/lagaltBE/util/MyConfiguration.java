package com.lagaltBE.lagaltBE.util;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

@Configuration
public class MyConfiguration {

    public MyConfiguration() {
        super();
    }

    @Bean
    public WebMvcConfigurer corsConfigurer() {
        return new WebMvcConfigurerAdapter() {
            @Override
            public void addCorsMappings(CorsRegistry registry) {
                registry.addMapping("/**")
                    .allowedMethods("HEAD", "GET", "PUT", "POST", "DELETE", "PATCH")
                    .allowedOrigins("https://lagalt-java.herokuapp.com", "http://localhost:3000");
            }
        };

    }
}