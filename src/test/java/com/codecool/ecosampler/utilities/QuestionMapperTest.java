package com.codecool.ecosampler.utilities;

import com.codecool.ecosampler.controller.dto.question.QuestionDTO;
import com.codecool.ecosampler.domain.FieldStyle;
import com.codecool.ecosampler.domain.Question;
import org.junit.jupiter.api.Test;

import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertEquals;

class QuestionMapperTest {
    @Test
    void should_return_a_dto() {
        UUID uuid = UUID.randomUUID();
        Question question = new Question(uuid, "", FieldStyle.SHORT_TEXT);
        QuestionDTO expected = new QuestionDTO(uuid, "", FieldStyle.SHORT_TEXT);
        QuestionDTO actual = QuestionMapper.toDTO(question);
        assertEquals(expected, actual);
    }
}