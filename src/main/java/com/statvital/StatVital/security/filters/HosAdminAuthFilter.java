package com.statvital.StatVital.security.filters;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.statvital.StatVital.dtos.request.SignInHospitalRequest;
import com.statvital.StatVital.dtos.response.LogInAdminResponse;
import com.statvital.StatVital.security.services.JwtService;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.stereotype.Component;

import static com.statvital.StatVital.utils.AppUtils.ACCESS_TOKEN_FIELD_VALUE;

import java.io.IOException;
import java.io.InputStream;
import java.util.Map;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

@RequiredArgsConstructor
@Slf4j
public class HosAdminAuthFilter extends UsernamePasswordAuthenticationFilter {
    private final AuthenticationManager authenticationManager;
    private ObjectMapper mapper = new ObjectMapper();

    private final JwtService jwtService;

    @Override
    public Authentication attemptAuthentication(HttpServletRequest request,
                                                HttpServletResponse response)
            throws AuthenticationException {
        try (InputStream stream = request.getInputStream()) {
            log.info("I got here");
            SignInHospitalRequest sign = mapper.readValue(stream, SignInHospitalRequest.class);
            String username = sign.getEmail();
            String password = sign.getPassword();


            log.info("username--->{} password--->{}", username, password);

            Authentication authentication = new UsernamePasswordAuthenticationToken(username, password);
            Authentication authenticationResult = authenticationManager.authenticate(authentication);
            log.info("auth result--> {}", authenticationResult);
            SecurityContext context = SecurityContextHolder.getContext();
            context.setAuthentication(authenticationResult);
            return authenticationResult;
        } catch (IOException exception) {
            throw new BadCredentialsException("Authentication failed");
        }

    }
    @Override
    protected void successfulAuthentication(HttpServletRequest request,
                                            HttpServletResponse response,
                                            FilterChain chain,
                                            Authentication authResult) throws IOException, ServletException {
        LogInAdminResponse loginResponse = new LogInAdminResponse();
        loginResponse.setLoggedIn(true);
        loginResponse.setLogInDate(loginResponse.getLogInDate());
        loginResponse.setMessage("Successfully Logged In");
        String token = jwtService.generateTokenFor(authResult.getPrincipal().toString());
        System.out.println(token);
        response.setContentType(APPLICATION_JSON_VALUE);
        response.getOutputStream().write(mapper.writeValueAsBytes(Map.of(ACCESS_TOKEN_FIELD_VALUE, token)));
        response.flushBuffer();
        response.setContentType(APPLICATION_JSON_VALUE);
        response.getOutputStream().flush();
    }
}
