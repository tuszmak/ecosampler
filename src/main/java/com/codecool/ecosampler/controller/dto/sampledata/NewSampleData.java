package com.codecool.ecosampler.controller.dto.sampledata;

import com.codecool.ecosampler.controller.dto.answer.NewAnswer;

import java.util.List;
import java.util.UUID;

public record NewSampleData(UUID userID, UUID formID, List<NewAnswer> newAnswers) {
}
