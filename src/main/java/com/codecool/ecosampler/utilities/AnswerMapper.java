package com.codecool.ecosampler.utilities;

import com.codecool.ecosampler.controller.dto.answer.AnswerDTO;
import com.codecool.ecosampler.domain.Answer;

public class AnswerMapper {
    public static AnswerDTO toDto(Answer answer) {
        return new AnswerDTO(answer.getPublicId(),
                answer.getAnswer(),
                answer.getQuestion().getPublicId()
        );
    }
}
