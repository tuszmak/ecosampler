package com.codecool.ecosampler.controller;

import com.codecool.ecosampler.service.FormService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/form")
public class FormController {
    private FormService formService;
    @PostMapping("/")
    public Long createNewForm(@RequestBody String name){
        return formService.createNewForm(name);
    }
}
