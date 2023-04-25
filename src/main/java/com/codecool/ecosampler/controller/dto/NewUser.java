package com.codecool.ecosampler.controller.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.Column;

public record NewUser(String email, String name,  String password) {
}
