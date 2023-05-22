package com.codecool.ecosampler.controller;


import com.codecool.ecosampler.controller.dto.login.LoginCredentials;
import com.codecool.ecosampler.controller.dto.login.LoginRequest;
import com.codecool.ecosampler.service.LoginService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/v1/login")
public class LoginController {
    private final LoginService loginService;

    @PostMapping
    public LoginCredentials verifyUser(@RequestBody LoginRequest loginRequest) {
        return loginService.verifyUser(loginRequest);
    }
}
