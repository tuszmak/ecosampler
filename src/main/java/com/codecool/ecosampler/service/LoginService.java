package com.codecool.ecosampler.service;

import com.codecool.ecosampler.controller.dto.login.LoginCredentials;
import com.codecool.ecosampler.controller.dto.login.LoginRequest;
import com.codecool.ecosampler.domain.User;
import com.codecool.ecosampler.security.JWTService;
import com.codecool.ecosampler.utilities.UserMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class LoginService {
    private final AuthenticationManager authenticationManager;
    private final JWTService jwtService;
    private final UserService userService;

    public LoginCredentials verifyUser(LoginRequest loginRequest) {
        Authentication authentication = authenticateUser(loginRequest);
        User user = userService.getUserByEmail(loginRequest.email());
        String token = jwtService.generateToken(authentication);
        return UserMapper.toLoginCredential(user, token);
    }

    private Authentication authenticateUser(LoginRequest loginRequest) {
        Authentication authentication = authenticationManager
                .authenticate(new UsernamePasswordAuthenticationToken(
                        loginRequest.email(),
                        loginRequest.password()
                ));
                SecurityContextHolder.getContext().setAuthentication(authentication);
        return authentication;
    }
}
