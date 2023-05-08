package com.codecool.ecosampler.utilities;

import com.codecool.ecosampler.controller.dto.question.QuestionDTO;
import com.codecool.ecosampler.domain.Question;
import org.springframework.stereotype.Service;

@Service
public class QuestionMapper {
    public QuestionDTO toDTO(Question question) {
        return new QuestionDTO(question.getPublicId(),
                question.getDescription(),
                question.getFieldStyle()
        );
    }
}
