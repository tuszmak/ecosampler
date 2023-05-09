package com.codecool.ecosampler.controller.dto.login;

import com.codecool.ecosampler.domain.Role;

import java.util.UUID;

public record ResponseUser(UUID id, Role role) {
}
