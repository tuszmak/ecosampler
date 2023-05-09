package com.codecool.ecosampler.service;

import com.codecool.ecosampler.controller.dto.login.RequestUser;
import com.codecool.ecosampler.controller.dto.login.ResponseUser;
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

    private UserRepository userRepository;
    public ResponseUser verifyUser(RequestUser requestUser) {
       return Mapper.mapToResponseUser(getUserByLogin(requestUser));
    }

    private User getUserByLogin(RequestUser requestUser) {
        return userRepository.findByNameAndPassword(requestUser.name(), requestUser.password())
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.BAD_REQUEST, "Wrong username or password!"));
    }
}
