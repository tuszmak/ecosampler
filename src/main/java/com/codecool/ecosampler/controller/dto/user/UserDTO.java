package com.codecool.ecosampler.controller.dto.user;

import com.codecool.ecosampler.domain.Role;

public record UserDTO(String name, String email, Role role) {
}
