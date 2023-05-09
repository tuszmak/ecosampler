package com.codecool.ecosampler.controller;


import com.codecool.ecosampler.controller.dto.login.RequestUser;
import com.codecool.ecosampler.controller.dto.login.ResponseUser;
import com.codecool.ecosampler.service.LoginService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@AllArgsConstructor
@RestController
@RequestMapping("/api/v1/login")
public class LoginController {

    private LoginService loginService;

    @PostMapping
    @ResponseStatus(HttpStatus.ACCEPTED)
    public ResponseUser verifyUser(@RequestBody RequestUser requestUser) {
        System.out.println("Login");
        return loginService.verifyUser(requestUser);
    }
}
