package com.codecool.ecosampler.service;

import com.codecool.ecosampler.domain.Answer;
import com.codecool.ecosampler.domain.Question;
import com.codecool.ecosampler.repository.AnswerRepository;
import com.codecool.ecosampler.utilities.AnswerMapper;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.util.List;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

class AnswerServiceTest {

    private final AnswerRepository answerRepository;
    private final AnswerService answerService;
    private QuestionService questionService;

    public AnswerServiceTest() {
        this.answerRepository = Mockito.mock(AnswerRepository.class);
        this.answerService = new AnswerService(answerRepository, new AnswerMapper(), questionService);
    }

//    @BeforeEach
//    public void setup() {
//
//
//    }


    @Test
    void should_get_one_answer() {
        Answer answer = new Answer(UUID.randomUUID(), "Waaaaa", new Question());
        when(answerRepository.findAll()).thenReturn(List.of(answer));
        int numberOfAnswers = answerService.getAllAnswersDTO().size();
        assertEquals(1, numberOfAnswers);
        verify(answerRepository, times(1)).findAll();
    }

    @Test
    void should_save_one_answer() {
        UUID uuid = UUID.randomUUID();

    }

    @Test
    void modifyAnswer() {
    }

    @Test
    void getAnswerByPublicId() {
    }
}