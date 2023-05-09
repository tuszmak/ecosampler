package com.codecool.ecosampler.utilities;

import com.codecool.ecosampler.controller.dto.answer.AnswerDTO;
import com.codecool.ecosampler.domain.Answer;
import org.springframework.stereotype.Service;

@Service
public class AnswerMapper {
    public AnswerDTO toDto(Answer answer) {
        return new AnswerDTO(answer.getPublicId(),
                answer.getAnswer(),
                answer.getQuestion().getPublicId()
        );
    }
}
