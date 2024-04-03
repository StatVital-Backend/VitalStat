package com.statvital.StatVital.security.manager;

import lombok.AllArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.ProviderNotFoundException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.stereotype.Component;


@Component
@AllArgsConstructor
public class HospitalAuthenticationManager implements AuthenticationManager {
    private final AuthenticationProvider authenticationProvider;

    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
        System.out.println("i got to hospital authentication manager");
        Class<?> authClass = authentication.getClass();
        if (authenticationProvider.supports(authClass)) {
            return authenticationProvider.authenticate(authentication);
        }
        throw new ProviderNotFoundException("Authentication request failed");
    }
}
