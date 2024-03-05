package com.statvital.StatVital.services;

public interface JwtToken {
    String generateToken(String email, String password);

    String verifyToken(String Token);
}
