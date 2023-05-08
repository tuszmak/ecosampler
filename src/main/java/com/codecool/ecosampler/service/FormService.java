package com.codecool.ecosampler.service;

import com.codecool.ecosampler.controller.dto.form.FormDTO;
import com.codecool.ecosampler.controller.dto.form.NewForm;
import com.codecool.ecosampler.domain.Form;
import com.codecool.ecosampler.repository.FormRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.UUID;

@AllArgsConstructor
@Service
public class FormService {
    private FormRepository formRepository;

    public UUID createNewForm(NewForm form) {
        return formRepository.save(new Form(
                                UUID.randomUUID(),
                                form.name()
                        )
                )
                .getPublicId();
    }
}
