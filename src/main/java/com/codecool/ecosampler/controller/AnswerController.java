package com.codecool.ecosampler.controller;

import com.codecool.ecosampler.controller.dto.answer.AnswerDTO;
import com.codecool.ecosampler.controller.dto.answer.NewAnswer;
import com.codecool.ecosampler.service.AnswerService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@AllArgsConstructor
@RestController
@RequestMapping("/api/v1/answer")
public class AnswerController {

    private AnswerService answerService;


    @GetMapping
    public List<AnswerDTO> getAllAnswers() {
        return answerService.getAllAnswersDTO();
    }

    @PostMapping
    @ResponseStatus(value = HttpStatus.CREATED)
    public UUID createAnswer(@RequestBody NewAnswer answer) {
        return answerService.createAnswer(answer);
    }

    @PutMapping("/{publicId}")
    public UUID modifyAnswer(@PathVariable UUID publicId, @RequestBody AnswerDTO requestAnswer) {
        return answerService.modifyAnswer(publicId, requestAnswer);
    }

    @DeleteMapping("/{publicId}")
    public void modifyAnswer(@PathVariable UUID publicId) {
        answerService.deleteAnswer(publicId);
    }
}
