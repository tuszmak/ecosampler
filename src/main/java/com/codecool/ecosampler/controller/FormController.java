package com.codecool.ecosampler.controller;

import com.codecool.ecosampler.controller.dto.form.FormDTO;
import com.codecool.ecosampler.service.FormService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.UUID;

@AllArgsConstructor
@RestController
@RequestMapping("/api/v1/form")
public class FormController {
    private FormService formService;

    @GetMapping("/by-project-id/{projectId}")
    public List<FormDTO> getFormsByProjectID(@PathVariable UUID projectId) {
        return formService.getFormsByProjectID(projectId);
    }
}
