package com.codecool.ecosampler.utilities;

import com.codecool.ecosampler.controller.dto.TokenDTO;

public class TokenMapper {
    public static TokenDTO toTokenDTO(String token) {
        return new TokenDTO(token);
    }
}
