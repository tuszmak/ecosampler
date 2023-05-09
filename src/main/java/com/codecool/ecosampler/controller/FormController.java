package com.codecool.ecosampler.controller;

import com.codecool.ecosampler.controller.dto.form.NewForm;
import com.codecool.ecosampler.service.FormService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@AllArgsConstructor
@RestController
@RequestMapping("/api/v1/form")
public class FormController {
    private FormService formService;

    @PostMapping("/")
    @ResponseStatus(value = HttpStatus.CREATED)
    public UUID createNewForm(@RequestBody NewForm form) {
        return formService.createNewForm(form);
    }
}
