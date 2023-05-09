package com.codecool.ecosampler.service;

import com.codecool.ecosampler.controller.dto.form.FormDTO;
import com.codecool.ecosampler.controller.dto.form.NewForm;
import com.codecool.ecosampler.domain.Form;
import com.codecool.ecosampler.exeption.NotFoundException;
import com.codecool.ecosampler.repository.FormRepository;
import com.codecool.ecosampler.utilities.FormMapper;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.UUID;

@AllArgsConstructor
@Service
public class FormService {
    private final FormRepository formRepository;
    private final FormMapper formMapper;

    public FormDTO createNewForm(NewForm newForm) {
        final Form form = formRepository.save(new Form(
                        UUID.randomUUID(),
                        newForm.name()
                )
        );
        return formMapper.toDTO(form);
    }

    public Form getFormByPublicId(UUID publicId) {
        return formRepository.findFormByPublicId(publicId)
                .orElseThrow(() -> new NotFoundException("Can't find data with this id: " + publicId));
    }
}
