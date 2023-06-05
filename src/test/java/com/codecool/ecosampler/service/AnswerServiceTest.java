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
//
//        NewAnswer answer1 = new NewAnswer("Test", UUID.randomUUID());
//
//        when(questionService.getQuestionByPublicId(any(UUID.class))).thenReturn(null);
//        List<Answer> answers = answerService.createListOfAnswers(List.of(answer1), null);
//        when(answerRepository.saveAll(anyCollection()))
//                .thenAnswer(invocationOnMock -> invocationOnMock.getArguments()[0]);
//        assertEquals(1, answers.size());
//        assertEquals(Answer.class, answers.get(0).getClass());
    }

    @Test
    void modifyAnswer() {
    }

    @Test
    void getAnswerByPublicId() {
    }
}