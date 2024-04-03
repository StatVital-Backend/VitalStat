package com.statvital.StatVital.security.services;

import org.springframework.security.core.userdetails.UserDetails;

public interface JwtService {
    String generateTokenFor(String email);

    boolean validate(String token);

    UserDetails extractUserDetailsFrom(String token);

}
