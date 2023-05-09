package com.codecool.ecosampler.controller;


import com.codecool.ecosampler.controller.dto.login.RequestUser;
import com.codecool.ecosampler.controller.dto.login.ResponseUser;
import com.codecool.ecosampler.service.LoginService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@AllArgsConstructor
@RestController
@RequestMapping("/api/v1/login")
public class LoginController {

    private LoginService loginService;

    @PostMapping
    public ResponseUser verifyUser(@RequestBody RequestUser requestUser) {
        System.out.println("Login");
        return loginService.verifyUser(requestUser);
    }
}
