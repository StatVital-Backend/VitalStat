package com.statvital.StatVital.security.services;

import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.statvital.StatVital.security.JwtConfig;
import com.statvital.StatVital.security.services.JwtService;
import lombok.AllArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Service;
import com.auth0.jwt.JWT;


import java.time.Instant;
import java.time.LocalDateTime;

import static com.auth0.jwt.algorithms.Algorithm.HMAC512;
import static java.time.ZoneOffset.UTC;

@Service
@AllArgsConstructor
public class JwtServiceImpl implements JwtService {
    private final JwtConfig jwtConfig;
    @Override
    public String generateTokenFor(String email) {
        System.out.println("I got to generate token");
        return JWT.create()
                .withSubject("access_token")
                .withClaim("username", email)
                .withExpiresAt(LocalDateTime.now().plusDays(Long.parseLong(jwtConfig.getJwtSecretKey()))
                        .toInstant(UTC))
                .sign(HMAC512(jwtConfig.getJwtSecretKey().getBytes()));


    }

    @Override
    public boolean validate(String token) {
        System.out.println("I got to validate token");

        Algorithm algorithm = HMAC512(jwtConfig.getJwtSecretKey().getBytes());
        DecodedJWT decodedJWT = JWT.require(algorithm)
                .build().verify(token);
        return isValidToken(decodedJWT);
    }

    private static boolean isValidToken(DecodedJWT decodedJWT) {
        return isTokenNotExpired(decodedJWT) && isTokenWithValidIssuer(decodedJWT);
    }

    private static boolean isTokenWithValidIssuer(DecodedJWT decodedJWT) {
        return decodedJWT.getIssuer().equals("sync");
    }

    private static boolean isTokenNotExpired(DecodedJWT decodedJWT) {
        return Instant.now().isAfter(decodedJWT.getExpiresAtAsInstant());
    }

    @Override
    public UserDetails extractUserDetailsFrom(String token) {
        return null;
    }
}
