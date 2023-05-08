package com.codecool.ecosampler.controller.dto.answer;

import java.util.UUID;

public record AnswerDTO(UUID id, String answer, UUID questionId) {
}
