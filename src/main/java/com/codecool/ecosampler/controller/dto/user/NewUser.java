package com.codecool.ecosampler.controller.dto.user;

import com.codecool.ecosampler.domain.Role;

import java.util.UUID;

public record NewUser(UUID public_id, String name, String email, Role role, String password) {
}
