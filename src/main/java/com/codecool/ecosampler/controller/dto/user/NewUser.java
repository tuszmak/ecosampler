package com.codecool.ecosampler.controller.dto.user;

import com.codecool.ecosampler.domain.Role;

public record NewUser(String name, String email, Role role, String password) {
}
