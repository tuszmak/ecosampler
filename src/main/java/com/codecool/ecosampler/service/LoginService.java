package com.codecool.ecosampler.service;

import com.codecool.ecosampler.controller.dto.login.LoginRequest;
import com.codecool.ecosampler.controller.dto.login.LoginCredentials;
import com.codecool.ecosampler.domain.User;
import com.codecool.ecosampler.repository.UserRepository;
import com.codecool.ecosampler.utilities.Mapper;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

@AllArgsConstructor
@Service
public class LoginService {

    private final UserRepository userRepository;
    public LoginCredentials verifyUser(LoginRequest requestUser) {
       return Mapper.mapToResponseUser(getUserByLogin(requestUser));
    }

    private User getUserByLogin(LoginRequest requestUser) {
        return userRepository.findByEmailAndPassword(requestUser.email(), requestUser.password())
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.BAD_REQUEST, "Wrong username or password!"));
    }
}
