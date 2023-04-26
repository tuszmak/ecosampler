package com.codecool.ecosampler.domain.controller;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/form")
public class FormController {
    @PostMapping("/")
    public Long createNewForm(@RequestBody String name){
        return formService.createForm(String name);
    }
}
