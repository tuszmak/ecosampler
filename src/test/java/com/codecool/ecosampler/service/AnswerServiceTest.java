package com.codecool.ecosampler.service;

import com.codecool.ecosampler.domain.Answer;
import com.codecool.ecosampler.domain.Question;
import com.codecool.ecosampler.repository.AnswerRepository;
import com.codecool.ecosampler.repository.QuestionRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;
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
    void should_get_one_answer() {
        Answer answer = new Answer(UUID.randomUUID(), "Test", new Question(), null);
        when(answerRepository.findAll()).thenReturn(List.of(answer));
        int numberOfAnswers = answerService.getAllAnswersDTO().size();
        assertEquals(1, numberOfAnswers);
        verify(answerRepository, times(1)).findAll();
    }

    @Test
    void should_get_five_answers() {
        Answer answer1 = new Answer(UUID.randomUUID(), "Test", new Question(), null);
        Answer answer2 = new Answer(UUID.randomUUID(), "Test", new Question(), null);
        Answer answer3 = new Answer(UUID.randomUUID(), "Test", new Question(), null);
        Answer answer4 = new Answer(UUID.randomUUID(), "Test", new Question(), null);
        Answer answer5 = new Answer(UUID.randomUUID(), "Test", new Question(), null);
        when(answerRepository.findAll()).thenReturn(List.of(answer1, answer2, answer3, answer4, answer5));
        int numberOfAnswers = answerService.getAllAnswersDTO().size();
        assertEquals(5, numberOfAnswers);
        verify(answerRepository, times(1)).findAll();
    }


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