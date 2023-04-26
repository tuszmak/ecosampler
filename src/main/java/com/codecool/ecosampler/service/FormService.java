package com.codecool.ecosampler.service;

import com.codecool.ecosampler.domain.Form;
import com.codecool.ecosampler.repository.FormRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@AllArgsConstructor
@Service
public class FormService {
    private FormRepository formRepository;

    public Long createNewForm(Form form) {
        return formRepository.save(form)
                .getId();
    }
}
