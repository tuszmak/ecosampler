package com.codecool.ecosampler.controller;

import org.springframework.web.bind.annotation.GetMapping;


public class HomeController {

    @GetMapping("/")
    public String index() {
        return "redirect:/index.html";
    }
}
