package com.statvital.StatVital.security.services;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;

@RequiredArgsConstructor
@Configuration
@Getter
public class JwtConfiguration {
    @Value("${jwt.signing.keys}")
    private String jwtSecretKey;
    @Value("${jwt.expiry.days}")
    private String jwtDuration;


    public String getJwtSecret(){
        return jwtSecretKey;
    }
    public int getJwtDuration(){
        return Integer.parseInt(jwtDuration);
    }

}
