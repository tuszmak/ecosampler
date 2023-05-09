package com.codecool.ecosampler.utilities;

import com.codecool.ecosampler.controller.dto.form.FormDTO;
import com.codecool.ecosampler.domain.Form;
import org.springframework.stereotype.Service;

@Service
public class FormMapper {
    public FormDTO toDTO(Form form) {
        return new FormDTO(form.getPublicId(),
                form.getName()
        );
    }
}
