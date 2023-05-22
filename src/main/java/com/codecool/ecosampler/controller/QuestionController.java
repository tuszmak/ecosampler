package com.codecool.ecosampler.controller;

import com.codecool.ecosampler.controller.dto.question.NewQuestion;
import com.codecool.ecosampler.controller.dto.question.QuestionDTO;
import com.codecool.ecosampler.service.FormService;
import com.codecool.ecosampler.service.QuestionService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@AllArgsConstructor
@RestController
@RequestMapping("/api/v1/question")
public class QuestionController {

    private QuestionService questionService;
    private FormService formService;

    @GetMapping
    public List<QuestionDTO> getAllQuestionsDTO() {
        return questionService.getAllQuestionsDTO();
    }

    @GetMapping("/getQuestions/{formID}")
    List<QuestionDTO> getFormQuestionsByFormID(@PathVariable UUID formID) {
        return formService.getQuestionDTOsByFormID(formID);
    }

    @PostMapping
    @ResponseStatus(value = HttpStatus.CREATED)
    public QuestionDTO createQuestion(@RequestBody NewQuestion question) {
        return questionService.createQuestion(question);
    }

    @PutMapping("/{publicId}")
    public UUID modifyQuestion(@PathVariable UUID publicId, @RequestBody QuestionDTO newQuestion) {
        return questionService.modifyQuestion(publicId, newQuestion);
    }

    @DeleteMapping("/{publicId}")
    public void deleteQuestion(@PathVariable UUID publicId) {
        questionService.deleteQuestion(publicId);
    }
}
