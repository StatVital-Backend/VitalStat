package com.statvital.StatVital.security;


import com.statvital.StatVital.security.filters.HosAdminAuthFilter;
import com.statvital.StatVital.security.filters.JwtAuthorizationFilter;
import com.statvital.StatVital.security.services.JwtService;
import com.statvital.StatVital.security.services.JwtServiceImpl;
import lombok.AllArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;


import static org.springframework.http.HttpMethod.POST;

@Configuration
@AllArgsConstructor
public class SecurityConfiguration {
    private final AuthenticationManager authenticationManager;
    private final JwtService jwtService;

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        var authenticationFilter = new HosAdminAuthFilter(authenticationManager, jwtService);
        authenticationFilter.setFilterProcessesUrl("*");
        return http.csrf(AbstractHttpConfigurer::disable)
                .cors(Customizer.withDefaults())
                .sessionManagement(c->c.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
                .addFilterAt(authenticationFilter, UsernamePasswordAuthenticationFilter.class)
                .addFilterBefore(new JwtAuthorizationFilter((JwtServiceImpl) jwtService), authenticationFilter.getClass())
                .authorizeHttpRequests(c->c.requestMatchers(POST,"/api/v1/signUpHospital", "/api/",  "/api/v1/logInHospital", "/api/v1/registerChild")
                        .permitAll()
                        .anyRequest().authenticated()).build();
    }

}
