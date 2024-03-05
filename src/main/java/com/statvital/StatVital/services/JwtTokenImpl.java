package com.statvital.StatVital.services;

import java.util.Date;

import com.statvital.StatVital.config.AppConfig;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;


public class Token implements JwtToken{
    private static final String SECRET_KEY = AppConfig.DEFAULT_SECRET_KEY;
    private static final long EXPIRATION_TIME = 3600000; // 1 hour in milliseconds

    @Override
    public String generateToken(String email, String password) {
        Date now = new Date();
        Date expiryDate = new Date(now.getTime() + EXPIRATION_TIME);

        return Jwts.builder()
                .setSubject(email)
                .claim("password", password)
                .setIssuedAt(now)
                .setExpiration(expiryDate)
                .signWith(SignatureAlgorithm.HS256, SECRET_KEY)
                .compact();

    }
}
