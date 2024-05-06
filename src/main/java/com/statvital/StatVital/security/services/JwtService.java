package com.statvital.StatVital.security.services;

import org.springframework.security.core.userdetails.UserDetails;

public interface JwtService {
    String generateToken(UserDetails user);
    boolean validateToken(String token, UserDetails user);
}
