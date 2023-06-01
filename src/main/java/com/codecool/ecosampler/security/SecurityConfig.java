package com.codecool.ecosampler.security;

import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import static com.codecool.ecosampler.domain.Role.DIRECTOR;
import static com.codecool.ecosampler.domain.Role.PROJECT_LEADER;
import static org.springframework.http.HttpMethod.*;

@RequiredArgsConstructor
@Configuration
@EnableWebSecurity(debug = true)
public class SecurityConfig {
    private final JWTAuthenticationFilter jwtAuthenticationFilter;

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http
                .csrf(AbstractHttpConfigurer::disable)
                .addFilterBefore(jwtAuthenticationFilter, UsernamePasswordAuthenticationFilter.class)
                .sessionManagement(SecuritySessionManagement ->
                        SecuritySessionManagement.sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                )
                .authorizeHttpRequests(auth -> auth
                        .requestMatchers(
                                "/api/v1/login",
                                "/",
                                "/index.html",
                                "static/**",
                                "/favicon.ico",
                                "manifest.json"
                        ).permitAll()
                        .requestMatchers(POST,
                                "/api/v1/project",
                                "/api/v1/user/**"
                        ).hasAuthority(DIRECTOR.name())
                        .requestMatchers(DELETE,
                                "/api/v1/project/{publicProjectId}"
                        ).hasAuthority(DIRECTOR.name())
                        .requestMatchers(POST,
                                "/api/v1/project/addForm/{projectID}",
                                "/api/v1/form/**"
                        ).hasAnyAuthority(DIRECTOR.name(), PROJECT_LEADER.name())
                        .requestMatchers(PUT,
                                "/api/v1/question/{publicId}",
                                "/api/v1/project/modify-users/{projectID}"
                        ).hasAnyAuthority(DIRECTOR.name(), PROJECT_LEADER.name())
                        .requestMatchers(DELETE,
                                "/api/v1/question/{publicId}"
                        ).hasAnyAuthority(DIRECTOR.name(), PROJECT_LEADER.name())
                        .anyRequest().authenticated()
                );
        return http.build();
    }

    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration authenticationConfiguration) throws Exception {
        return authenticationConfiguration.getAuthenticationManager();
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

}
