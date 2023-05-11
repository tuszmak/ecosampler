package com.codecool.ecosampler.controller;


import com.codecool.ecosampler.controller.dto.login.LoginRequest;
import com.codecool.ecosampler.controller.dto.login.LoginCredentials;
import com.codecool.ecosampler.service.LoginService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

@AllArgsConstructor
@RestController
@RequestMapping("/api/v1/login")
public class LoginController {

    private LoginService loginService;

    @PostMapping
    public LoginCredentials verifyUser(@RequestBody LoginRequest loginRequest) {
        return loginService.verifyUser(loginRequest);
    }
}
