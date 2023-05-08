package com.codecool.ecosampler.controller.dto.question;

import com.codecool.ecosampler.domain.FieldStyle;

public record NewQuestion(String description, FieldStyle fieldStyle) {
}
