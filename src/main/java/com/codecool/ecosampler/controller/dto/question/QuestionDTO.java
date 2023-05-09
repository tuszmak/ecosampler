package com.codecool.ecosampler.controller.dto.question;

import com.codecool.ecosampler.domain.FieldStyle;

import java.util.UUID;

public record QuestionDTO(UUID id, String description, FieldStyle fieldStyle) {
}
