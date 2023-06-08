package com.codecool.ecosampler.utilities;

import com.codecool.ecosampler.controller.dto.question.QuestionDTO;
import com.codecool.ecosampler.domain.Question;

public class QuestionMapper {
    public static QuestionDTO toDTO(Question question) {
        return new QuestionDTO(question.getPublicId(),
                question.getDescription(),
                question.getFieldStyle()
        );
    }
}
