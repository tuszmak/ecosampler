package com.codecool.ecosampler.service;

import com.codecool.ecosampler.repository.AnswerRepository;
import com.codecool.ecosampler.repository.QuestionRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
@ExtendWith(MockitoExtension.class)
class AnswerServiceTest {



    @Mock
    private AnswerRepository answerRepository;
    @Mock
    private QuestionRepository questionRepository;
    @InjectMocks
    private QuestionService questionService;
    @InjectMocks
    private AnswerService answerService;

    @Test
    void should_save_one_answer() {

    }
}