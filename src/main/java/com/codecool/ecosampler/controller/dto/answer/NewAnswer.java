package com.codecool.ecosampler.controller.dto.answer;

import java.util.UUID;

public record NewAnswer(String answer, UUID questionID) {
}
