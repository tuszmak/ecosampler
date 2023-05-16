package com.codecool.ecosampler.service;

import com.codecool.ecosampler.controller.dto.login.LoginCredentials;
import com.codecool.ecosampler.controller.dto.login.LoginRequest;
import com.codecool.ecosampler.domain.User;
import com.codecool.ecosampler.repository.UserRepository;
import com.codecool.ecosampler.utilities.UserMapper;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

@AllArgsConstructor
@Service
public class LoginService {

    private final UserRepository userRepository;
    public LoginCredentials verifyUser(LoginRequest loginRequest) {
       return UserMapper.mapToResponseUser(getUserByLogin(loginRequest));
    }

    private User getUserByLogin(LoginRequest loginRequest) {
        return userRepository.findByEmailAndPassword(loginRequest.email(), loginRequest.password())
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.BAD_REQUEST, "Wrong email or password!"));
    }
}
