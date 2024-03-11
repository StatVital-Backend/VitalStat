package com.statvital.StatVital.config;

import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

public class GlobalCorsConfiguration implements WebMvcConfigurer {

    @Override
    public  void addCorsMappings(CorsRegistry registry){
        registry.addMapping("/**")
                .allowedMethods("POST","PUT", "GET", "PATCH", "DELETE")
                .allowedOrigins("")
                .allowedHeaders("*")
                .allowCredentials(true);

    }

}
