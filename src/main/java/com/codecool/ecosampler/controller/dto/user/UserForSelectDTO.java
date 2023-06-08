package com.codecool.ecosampler.controller.dto.user;

import com.codecool.ecosampler.domain.Role;

import java.util.UUID;

public record UserForSelectDTO(UUID id, String name, Role role) {
}
