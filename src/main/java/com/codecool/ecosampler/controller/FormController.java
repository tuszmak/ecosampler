package com.codecool.ecosampler.controller;

import com.codecool.ecosampler.controller.dto.form.FormDTO;
import com.codecool.ecosampler.controller.dto.form.NewForm;
import com.codecool.ecosampler.service.FormService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@AllArgsConstructor
@RestController
@RequestMapping("/api/v1/form")
public class FormController {
    private FormService formService;

    @PostMapping("/")
    @ResponseStatus(value = HttpStatus.CREATED)
    public FormDTO createNewForm(@RequestBody NewForm form) {
        return formService.createNewForm(form);
    }
    @GetMapping("/getForms/{projectID}")
    public List<FormDTO> getFormsByProjectID(@PathVariable UUID projectID){
        return formService.getFormsByProjectID(projectID);
    }
}
