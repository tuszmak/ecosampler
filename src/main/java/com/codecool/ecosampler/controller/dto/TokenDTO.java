package com.codecool.ecosampler.controller.dto;

import lombok.Getter;

@Getter
public class TokenDTO {
    private String accessToken;
    private String tokenType = "Bearer ";
    public TokenDTO(String accessToken) {
        this.accessToken = accessToken;
    }
}
