package com.codecool.ecosampler.controller;

import com.codecool.ecosampler.domain.Question;
import com.codecool.ecosampler.service.QuestionService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@AllArgsConstructor
@RestController
@RequestMapping("/api/v1/question")
public class QuestionController {

    private QuestionService questionService;

    @GetMapping
    public List<Question> getAllQuestions() {
        return questionService.getAllQuestions();
    }

    @ResponseStatus(value = HttpStatus.CREATED)
    @PostMapping
    public Long createQuestion(@RequestBody Question question) {
        return questionService.createQuestion(question);
    }
    @DeleteMapping("/{id}")
    public void deleteQuestion(@PathVariable Long id) {
        questionService.deleteQuestion(id);
    }
}
