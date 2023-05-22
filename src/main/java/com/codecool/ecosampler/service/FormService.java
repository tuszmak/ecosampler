package com.codecool.ecosampler.service;

import com.codecool.ecosampler.controller.dto.form.FormDTO;
import com.codecool.ecosampler.controller.dto.form.NewForm;
import com.codecool.ecosampler.controller.dto.question.QuestionDTO;
import com.codecool.ecosampler.domain.Form;
import com.codecool.ecosampler.exeption.NotFoundException;
import com.codecool.ecosampler.repository.FormRepository;
import com.codecool.ecosampler.utilities.FormMapper;
import com.codecool.ecosampler.utilities.QuestionMapper;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@AllArgsConstructor
@Service
public class FormService {
    private final FormRepository formRepository;
    private final FormMapper formMapper;
    private final QuestionMapper questionMapper;

    public FormDTO createNewFormGetDTO(NewForm newForm) {
        final Form form = formRepository.save(new Form(UUID.randomUUID(), newForm.name()));
        return formMapper.toDTO(form);
    }

    public Form createNewForm(NewForm newForm) {
        return formRepository.save(new Form(UUID.randomUUID(), newForm.name()));
    }

    public Form getFormByPublicId(UUID publicId) {
        return formRepository.findFormByPublicId(publicId)
                .orElseThrow(() -> new NotFoundException("Can't find data with this id: " + publicId));
    }

    public List<FormDTO> getFormsByProjectID(UUID projectID) {
        return formRepository.findFormsByProjectID(projectID).stream()
                .map(formMapper::toDTO).collect(Collectors.toList());

    }

    public List<QuestionDTO> getQuestionDTOsByFormID(UUID formID) {
        Form form = formRepository.findFormByPublicId(formID)
                .orElseThrow(() -> new RuntimeException("No form found"));
        return form.getQuestions().stream()
                .map(questionMapper::toDTO)
                .collect(Collectors.toList());
    }
}
