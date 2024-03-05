package com.statvital.StatVital.services;

import java.util.Date;

import com.statvital.StatVital.config.AppConfig;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class JwtTokenImpl implements JwtToken{
    private static final String SECRET_KEY = AppConfig.DEFAULT_SECRET_KEY;
    private static final long EXPIRATION_TIME = 3600000;

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

    @Override
    public String verifyToken(String token) {
        Claims claims = Jwts.parser()
                .setSigningKey(SECRET_KEY)
                .build()
                .parseClaimsJws(token)
                .getBody();


        //        String password = claims.ge
        return claims.getSubject();
    }

}
