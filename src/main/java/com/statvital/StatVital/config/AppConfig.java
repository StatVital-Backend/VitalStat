package com.statvital.StatVital.config;

import lombok.Getter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

@Configuration
@Getter
public class AppConfig {

    @Value("${mail.api.key}")
    private String mailApiKey;
    @Value("${mail.api.url}")
    private String mailServiceUrl;
//    @Value("${DEFAULT_SECRET_KEY}")
//    private String DEFAULT_SECRET_KEY;

    @Bean
    public RestTemplate restTemplate(){
        return new RestTemplate();
    }

}
