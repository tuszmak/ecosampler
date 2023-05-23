package com.codecool.ecosampler.utilities;

import com.codecool.ecosampler.controller.dto.form.FormDTO;
import com.codecool.ecosampler.domain.Form;

public class FormMapper {
    public static FormDTO toDTO(Form form) {
        return new FormDTO(form.getPublicId(),
                form.getName()
        );
    }
}
