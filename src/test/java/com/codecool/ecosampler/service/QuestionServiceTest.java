package com.codecool.ecosampler.service;

import com.codecool.ecosampler.controller.dto.question.NewQuestion;
import com.codecool.ecosampler.controller.dto.question.QuestionDTO;
import com.codecool.ecosampler.domain.FieldStyle;
import com.codecool.ecosampler.domain.Question;
import com.codecool.ecosampler.exception.BadRequestException;
import com.codecool.ecosampler.exception.NotFoundException;
import com.codecool.ecosampler.repository.QuestionRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
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
    public void setup() {
        uuid = UUID.randomUUID();
    }

    //createQuestion tests
    @Test
    void should_create_a_question() {
        NewQuestion newQuestion = new NewQuestion("Test", FieldStyle.SHORT_TEXT);
        Question question = new Question(uuid, newQuestion.description(), newQuestion.fieldStyle());
        when(questionRepository.save(any(Question.class))).thenReturn(question);
        QuestionDTO expected = new QuestionDTO(uuid, newQuestion.description(), newQuestion.fieldStyle());
        QuestionDTO actual = questionService.createQuestion(newQuestion);
        assertEquals(expected, actual);
    }

    @Test
    void should_throw_when_creating_question() {
        when(questionRepository.existsByDescription(any(String.class))).thenReturn(true);
        NewQuestion newQuestion = new NewQuestion("Test", FieldStyle.SHORT_TEXT);
        assertThrows(BadRequestException.class, () -> questionService.createQuestion(newQuestion));
        verify(questionRepository).existsByDescription(any(String.class));
    }

    //createMultipleQuestionsWhichDoesntExist tests
    @Test
    void should_create_three_questions() {
        NewQuestion newQuestion1 = new NewQuestion("Test1", FieldStyle.SHORT_TEXT);
        NewQuestion newQuestion2 = new NewQuestion("Test2", FieldStyle.SHORT_TEXT);
        NewQuestion newQuestion3 = new NewQuestion("Test3", FieldStyle.SHORT_TEXT);
        Question question = new Question(uuid, newQuestion1.description(), newQuestion1.fieldStyle());
        List<NewQuestion> questionList = List.of(newQuestion1, newQuestion2, newQuestion3);
        when(questionRepository.existsByDescription(any(String.class))).thenReturn(false);
        when(questionRepository.save(any(Question.class))).thenReturn(question);
        questionService.createMultipleQuestionsWhichDoesntExist(questionList);
        verify(questionRepository, times(3)).save(any(Question.class));
    }

    @Test
    void should_create_two_questions() {
        NewQuestion newQuestion1 = new NewQuestion("Test1", FieldStyle.SHORT_TEXT);
        NewQuestion newQuestion2 = new NewQuestion("Test2", FieldStyle.SHORT_TEXT);
        NewQuestion newQuestion3 = new NewQuestion("Test3", FieldStyle.SHORT_TEXT);
        Question question = new Question(uuid, newQuestion1.description(), newQuestion1.fieldStyle());
        List<NewQuestion> questionList = List.of(newQuestion1, newQuestion2, newQuestion3);
        when(questionRepository.existsByDescription("Test1")).thenAnswer(argument -> argument.getArgument(0) == "Test1");
        when(questionRepository.save(any(Question.class))).thenReturn(question);
        questionService.createMultipleQuestionsWhichDoesntExist(questionList);
        verify(questionRepository, times(2)).save(any(Question.class));

    }

    //searchMultipleQuestions
    @Test
    void should_return_nothing() {
        NewQuestion newQuestion1 = new NewQuestion("Test1", FieldStyle.SHORT_TEXT);
        when(questionRepository.findQuestionByDescription(any(String.class))).thenReturn(Optional.empty());
        List<Question> expected = new ArrayList<>();
        List<Question> actual = questionService.searchMultipleQuestions(List.of(newQuestion1));
        assertEquals(expected, actual);
        verify(questionRepository).findQuestionByDescription(anyString());
    }

    @Test
    void should_return_two_questions() {
        NewQuestion newQuestion1 = new NewQuestion("Test1", FieldStyle.SHORT_TEXT);
        NewQuestion newQuestion2 = new NewQuestion("Test2", FieldStyle.SHORT_TEXT);
        when(questionRepository.findQuestionByDescription(anyString())).thenAnswer(argument -> Optional.of(new Question(uuid, argument.getArgument(0), FieldStyle.SHORT_TEXT)));
        List<Question> actual = questionService.searchMultipleQuestions(List.of(newQuestion1, newQuestion2));
        assertEquals(2, actual.size());
        assertEquals(newQuestion1.description(), actual.get(0).getDescription());
        verify(questionRepository, times(2)).findQuestionByDescription(anyString());
    }

    //ModifyQuestion tests
    @Test
    void should_succeed_with_nothing_changed() {
        QuestionDTO questionDTO = new QuestionDTO(uuid, null, null);
        Question question = new Question(uuid, "test", FieldStyle.SHORT_TEXT);
        UUID expected = uuid;
        when(questionRepository.findQuestionByPublicId(any(UUID.class))).thenReturn(Optional.of(question));
        when(questionRepository.save(any(Question.class))).thenReturn(question);
        UUID actual = questionService.modifyQuestion(uuid, questionDTO);
        assertEquals(expected, actual);
        verify(questionRepository).findQuestionByPublicId(any(UUID.class));
        verify(questionRepository).save(any(Question.class));
    }

    @Test
    void should_succeed_with_no_description() {
        QuestionDTO questionDTO = new QuestionDTO(uuid, null, FieldStyle.SHORT_TEXT);
        Question question = new Question(uuid, "test", FieldStyle.SHORT_TEXT);
        UUID expected = uuid;
        when(questionRepository.findQuestionByPublicId(any(UUID.class))).thenReturn(Optional.of(question));
        when(questionRepository.save(any(Question.class))).thenReturn(question);
        UUID actual = questionService.modifyQuestion(uuid, questionDTO);
        assertEquals(expected, actual);
        verify(questionRepository).findQuestionByPublicId(any(UUID.class));
        verify(questionRepository).save(any(Question.class));
    }

    @Test
    void should_succeed_with_no_fieldstyle() {
        QuestionDTO questionDTO = new QuestionDTO(uuid, "valami", null);
        Question question = new Question(uuid, "test", FieldStyle.SHORT_TEXT);
        UUID expected = uuid;
        when(questionRepository.findQuestionByPublicId(any(UUID.class))).thenReturn(Optional.of(question));
        when(questionRepository.save(any(Question.class))).thenReturn(question);
        UUID actual = questionService.modifyQuestion(uuid, questionDTO);
        assertEquals(expected, actual);
        verify(questionRepository).findQuestionByPublicId(any(UUID.class));
        verify(questionRepository).save(any(Question.class));
    }

    @Test
    void should_succeed_with_everything() {
        QuestionDTO questionDTO = new QuestionDTO(uuid, "valami", FieldStyle.SHORT_TEXT);
        Question question = new Question(uuid, "test", FieldStyle.SHORT_TEXT);
        UUID expected = uuid;
        when(questionRepository.findQuestionByPublicId(any(UUID.class))).thenReturn(Optional.of(question));
        when(questionRepository.save(any(Question.class))).thenReturn(question);
        UUID actual = questionService.modifyQuestion(uuid, questionDTO);
        assertEquals(expected, actual);
        verify(questionRepository).findQuestionByPublicId(any(UUID.class));
        verify(questionRepository).save(any(Question.class));
    }

    //deleteQuestion tests
    @Test
    void should_throw_when_bad_uuid() {
        when(questionRepository.findQuestionByPublicId(any(UUID.class))).thenReturn(Optional.empty());
        assertThrows(NotFoundException.class, () -> questionService.deleteQuestion(uuid));
        verify(questionRepository).findQuestionByPublicId(any());
    }

    @Test
    void should_delete_with_uuid() {
        Question question = new Question(uuid, "test", FieldStyle.SHORT_TEXT);
        when(questionRepository.findQuestionByPublicId(any(UUID.class))).thenReturn(Optional.of(question));
        questionService.deleteQuestion(uuid);
        verify(questionRepository).deleteById(any());
    }

    //findQuestionByPublicId tests
    @Test
    void should_throw_when_returns_nothing() {
        when(questionRepository.findQuestionByPublicId(any(UUID.class))).thenReturn(Optional.empty());
        assertThrows(NotFoundException.class, () -> questionService.getQuestionByPublicId(UUID.randomUUID()));
        verify(questionRepository).findQuestionByPublicId(any(UUID.class));
    }

    @Test
    void should_return_a_question_when_given_publicid() {
        Question question = new Question(uuid, "test", FieldStyle.SHORT_TEXT);
        when(questionRepository.findQuestionByPublicId(any(UUID.class))).thenReturn(Optional.of(question));
        assertEquals(question, questionService.getQuestionByPublicId(uuid));
        verify(questionRepository).findQuestionByPublicId(any(UUID.class));
    }
}