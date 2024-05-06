package com.statvital.StatVital.config;

import com.statvital.StatVital.data.repository.HospitalAdminRepo;
import lombok.Getter;


import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Configuration
@Getter
@RequiredArgsConstructor
public class AppConfig {

    @Value("${mail.api.key}")
    private String mailApiKey;
    @Value("${mail.api.url}")
    private String mailServiceUrl;

    @Value("${DB_URL}")
    private String DB_URL;

    @Value("${DB_USER}")
    private String DB_USER;

    @Value("${DB_PASSWORD}")
    private String DB_PASSWORD;

    @Value("${DB_TABLE}")
    private String TABLE;

    private final HospitalAdminRepo hospitalAdminRepo;

    @Bean
    public UserDetailsService userDetailsService() {
        return facilityName -> hospitalAdminRepo.findHospitalAdminByFacilityName(facilityName)
                .orElseThrow(()-> new UsernameNotFoundException("Hospital not found"));

    }

    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration config) throws Exception {
        return config.getAuthenticationManager();
    }
    @Bean
    public RestTemplate restTemplate(){
        return new RestTemplate();
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

}
