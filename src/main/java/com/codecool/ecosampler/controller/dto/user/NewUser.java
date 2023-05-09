package com.codecool.ecosampler.controller.dto.user;

import java.util.UUID;

public record NewUser(UUID public_id, String name, String email, String password) {
}
