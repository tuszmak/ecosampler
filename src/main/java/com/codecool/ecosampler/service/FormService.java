package com.codecool.ecosampler.service;

import com.codecool.ecosampler.controller.dto.form.FormDTO;
import com.codecool.ecosampler.controller.dto.form.NewForm;
import com.codecool.ecosampler.controller.dto.question.QuestionDTO;
import com.codecool.ecosampler.domain.Form;
import com.codecool.ecosampler.domain.Question;
import com.codecool.ecosampler.exception.NotFoundException;
import com.codecool.ecosampler.repository.FormRepository;
import com.codecool.ecosampler.utilities.FormMapper;
import com.codecool.ecosampler.utilities.QuestionMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Service
public class FormService {
    private final FormRepository formRepository;
    private final QuestionService questionService;

    protected Form createNewForm(NewForm newForm) {
        questionService.createMultipleQuestionsWhichDoesntExist(newForm.questions());
        List<Question> questionsToDB = questionService.searchMultipleQuestions(newForm.questions());
        return formRepository.save(new Form(UUID.randomUUID(), newForm.name(), questionsToDB));
    }

    protected Form getFormByPublicId(UUID publicId) {
        return formRepository.findFormByPublicId(publicId)
                .orElseThrow(() -> new NotFoundException("Form doesn't exist with Id: " + publicId));
    }

    public List<FormDTO> getFormsByProjectID(UUID projectID) {
        return formRepository.findFormsByProjectID(projectID)
                .stream()
                .map(FormMapper::toDTO)
                .toList();
    }

    public List<QuestionDTO> getQuestionDTOsByFormID(UUID formID) {
        Form form = getFormByPublicId(formID);
        return form.getQuestions().stream()
                .map(QuestionMapper::toDTO)
                .collect(Collectors.toList());
    }
}
