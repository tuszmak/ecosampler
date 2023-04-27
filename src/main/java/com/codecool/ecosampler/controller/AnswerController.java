package com.codecool.ecosampler.controller;

import com.codecool.ecosampler.domain.Answer;
import com.codecool.ecosampler.service.AnswerService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@AllArgsConstructor
@RestController
@RequestMapping("/api/v1/answer")
public class AnswerController {

    private AnswerService answerService;

    @GetMapping
    public List<Answer> getAllAnswers() {
        return answerService.getAllAnswers();
    }

    @ResponseStatus(value = HttpStatus.CREATED)
    @PostMapping
    public Long createAnswer(@RequestBody Answer answer) {
        return answerService.createAnswer(answer);
    }

    @ResponseStatus(value = HttpStatus.CREATED)
    @PutMapping("/{id}")
    public Answer modifyAnswer(@PathVariable Long id, @RequestBody Answer requestAnswer) {
        return answerService.modifyAnswer(id, requestAnswer);
    }

    @DeleteMapping("/{id}")
    public void modifyAnswer(@PathVariable Long id) {
        answerService.deleteAnswer(id);
    }
}
