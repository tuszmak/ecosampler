package com.codecool.ecosampler.controller.dto.login;

import com.codecool.ecosampler.domain.Role;

import java.util.UUID;

public record LoginCredentials(UUID id, Role role, String name, String token) {
}
