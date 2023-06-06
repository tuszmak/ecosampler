package com.codecool.ecosampler.service;

import com.codecool.ecosampler.controller.dto.question.NewQuestion;
import com.codecool.ecosampler.controller.dto.question.QuestionDTO;
import com.codecool.ecosampler.domain.FieldStyle;
import com.codecool.ecosampler.domain.Question;
import com.codecool.ecosampler.exception.BadRequestException;
import com.codecool.ecosampler.repository.QuestionRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.*;
@ExtendWith(MockitoExtension.class)
class QuestionServiceTest {
    @Mock
    private QuestionRepository questionRepository;
    @InjectMocks
    private QuestionService questionService;
    private UUID uuid;
    @BeforeEach
    public void setup(){
        uuid = UUID.randomUUID();
    }
    //createQuestion tests
    @Test
    void should_create_a_question() {
        NewQuestion newQuestion = new NewQuestion("Test", FieldStyle.SHORT_TEXT);
        Question question = new Question(uuid,newQuestion.description(),newQuestion.fieldStyle());
        when(questionRepository.save(any(Question.class))).thenReturn(question);
        QuestionDTO expected = new QuestionDTO(uuid, newQuestion.description(), newQuestion.fieldStyle());
        QuestionDTO actual = questionService.createQuestion(newQuestion);
        assertEquals(expected, actual);
    }
    @Test
    void should_throw_when_creating_question(){
        when(questionRepository.existsByDescription(any(String.class))).thenReturn(true);
        NewQuestion newQuestion = new NewQuestion("Test", FieldStyle.SHORT_TEXT);
        assertThrows(BadRequestException.class, ()-> questionService.createQuestion(newQuestion));
        verify(questionRepository).existsByDescription(any(String.class));
    }
    //createMultipleQuestionsWhichDoesntExist tests
    @Test
    void should_create_three_questions() {
        NewQuestion newQuestion1 = new NewQuestion("Test1", FieldStyle.SHORT_TEXT);
        NewQuestion newQuestion2 = new NewQuestion("Test2", FieldStyle.SHORT_TEXT);
        NewQuestion newQuestion3 = new NewQuestion("Test3", FieldStyle.SHORT_TEXT);
        Question question = new Question(uuid,newQuestion1.description(),newQuestion1.fieldStyle());
        List<NewQuestion> questionList = List.of(newQuestion1,newQuestion2,newQuestion3);
        when(questionRepository.existsByDescription(any(String.class))).thenReturn(false);
        when(questionRepository.save(any(Question.class))).thenReturn(question);
        questionService.createMultipleQuestionsWhichDoesntExist(questionList);
        verify(questionRepository,times(3)).save(any(Question.class));
    }
    //TODO sad path for this function
    @Test
    void searchMultipleQuestions() {
    }

    @Test
    void modifyQuestion() {
    }

    @Test
    void deleteQuestion() {
    }

    @Test
    void getQuestionByPublicId() {
    }
}