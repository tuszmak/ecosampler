package com.codecool.ecosampler.service;

import com.codecool.ecosampler.controller.dto.form.FormDTO;
import com.codecool.ecosampler.controller.dto.form.NewForm;
import com.codecool.ecosampler.controller.dto.question.NewQuestion;
import com.codecool.ecosampler.controller.dto.question.QuestionDTO;
import com.codecool.ecosampler.domain.FieldStyle;
import com.codecool.ecosampler.domain.Form;
import com.codecool.ecosampler.domain.Question;
import com.codecool.ecosampler.exception.NotFoundException;
import com.codecool.ecosampler.repository.FormRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class FormServiceTest {
    @Mock
    private FormRepository formRepository;
    @Mock
    private QuestionService questionService;

    @InjectMocks
    private FormService formService;

    @Test
    void createNewForm_shouldSaveForm() {
        UUID uuid = UUID.randomUUID();
        List<NewQuestion> newQuestions = List.of(new NewQuestion("It is a question", FieldStyle.CHECK_BOX));
        NewForm newForm = new NewForm("form", newQuestions);
        Form form = new Form(uuid, "Form");
        Question question = new Question(UUID.randomUUID(), "x", FieldStyle.CHECK_BOX);
        when(formRepository.save(any(Form.class))).thenReturn(form);
        when(questionService.searchMultipleQuestions(newQuestions)).thenReturn(List.of(question));
        Form savedForm = formService.createNewForm(newForm);
        assertEquals(uuid, savedForm.getPublicId());
        verify(formRepository).save(any(Form.class));
        verify(questionService).searchMultipleQuestions(newQuestions);
        verify(questionService).createMultipleQuestionsWhichDoesntExist(newQuestions);
    }

    @Test
    void getFormByPublicId_shouldReturnTheFormWithGivenId() {
        UUID uuid = UUID.randomUUID();
        Form form = new Form(uuid, "Form");
        when(formRepository.findFormByPublicId(uuid)).thenReturn(Optional.of(form));
        Form returnedform = formService.getFormByPublicId(uuid);
        assertEquals(uuid, returnedform.getPublicId());
        verify(formRepository).findFormByPublicId(uuid);
    }

    @Test
    void getFormByPublicId_shouldThrowNotFoundException() {
        UUID uuid = UUID.randomUUID();
        when(formRepository.findFormByPublicId(uuid)).thenReturn(Optional.empty());
        assertThrows(NotFoundException.class, () -> formService.getFormByPublicId(uuid));
        verify(formRepository).findFormByPublicId(uuid);
    }

    @Test
    void getFormsByProjectID_shouldReturnTheFormDTO() {
        UUID uuid = UUID.randomUUID();
        Form form = new Form(uuid, "form");
        when(formRepository.findFormsByProjectID(uuid)).thenReturn(List.of(form));
        List<FormDTO> formsByProjectID = formService.getFormsByProjectID(uuid);
        assertEquals(formsByProjectID.size(), 1);
        assertEquals(form.getName(), formsByProjectID.get(0).name());
        verify(formRepository).findFormsByProjectID(uuid);
    }

    @Test
    void getFormsByProjectID_shouldReturnEmptyList() {
        UUID uuid = UUID.randomUUID();
        when(formRepository.findFormsByProjectID(uuid)).thenReturn(List.of());
        List<FormDTO> formsByProjectID = formService.getFormsByProjectID(uuid);
        assertEquals(0, formsByProjectID.size());
        verify(formRepository).findFormsByProjectID(uuid);
    }

    @Test
    void getQuestionDTOsByFormID_shouldReturnTheQuestionDTOs() {
        UUID uuid = UUID.randomUUID();
        final Question question = new Question(uuid, "question", FieldStyle.CHECK_BOX);
        final Form form = new Form(uuid, "form", List.of(question));
        when(formRepository.findFormByPublicId(uuid)).thenReturn(Optional.of(form));
        final List<QuestionDTO> questionDTOsByFormID = formService.getQuestionDTOsByFormID(uuid);
        assertEquals(1, questionDTOsByFormID.size());
        assertEquals(question.getDescription(), questionDTOsByFormID.get(0).description());
        verify(formRepository).findFormByPublicId(uuid);
    }
}
