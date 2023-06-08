package com.codecool.ecosampler.controller.dto.user;

import com.codecool.ecosampler.domain.Role;

import java.util.UUID;

public record UserDTO(UUID id, String name, String email, Role role) {
}
