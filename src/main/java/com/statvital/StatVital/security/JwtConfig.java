package com.statvital.StatVital.security;

import lombok.Getter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;

@Configuration

public class JwtConfig {

    @Getter
    @Value("${jwt.secret.keys")
    private String jwtSecretKey;
    @Value("${jwt.expiry.days}")
    private String jwtDuration;


    public int getJwtDuration() {
        return Integer.parseInt(jwtDuration);
    }
}
