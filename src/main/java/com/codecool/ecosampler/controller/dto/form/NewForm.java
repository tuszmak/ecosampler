package com.codecool.ecosampler.controller.dto.form;

import com.codecool.ecosampler.controller.dto.question.NewQuestion;

import java.util.List;

public record NewForm(String name, List<NewQuestion> questions) {
}
