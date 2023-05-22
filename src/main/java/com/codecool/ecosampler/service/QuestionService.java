package com.codecool.ecosampler.service;

import com.codecool.ecosampler.controller.dto.question.NewQuestion;
import com.codecool.ecosampler.controller.dto.question.QuestionDTO;
import com.codecool.ecosampler.domain.Question;
import com.codecool.ecosampler.exeption.BadRequestException;
import com.codecool.ecosampler.exeption.NotFoundException;
import com.codecool.ecosampler.repository.QuestionRepository;
import com.codecool.ecosampler.utilities.QuestionMapper;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.UUID;
import java.util.stream.Collectors;

@AllArgsConstructor
@Service
public class QuestionService {
    private final QuestionRepository questionRepository;
    private final QuestionMapper questionMapper;

    public List<QuestionDTO> getAllQuestionsDTO() {
        return questionRepository.findAll().stream()
                .map(questionMapper::toDTO)
                .collect(Collectors.toList());
    }

    public QuestionDTO createQuestion(NewQuestion newQuestion) {
        isQuestionExistByDescription(newQuestion.description());
        final Question question = questionRepository.save(new Question(UUID.randomUUID(),
                        newQuestion.description(),
                        newQuestion.fieldStyle()
                )
        );
        return questionMapper.toDTO(question);
    }

    protected List<Question> createMultipleQuestions(List<NewQuestion> newQuestions) {
        List<Question> questions = new ArrayList<>();
        for (NewQuestion newQuestion : newQuestions) {
            isQuestionExistByDescription(newQuestion.description());
            Question currentlyCreatedQuestion = new Question(
                    UUID.randomUUID(),
                    newQuestion.description(),
                    newQuestion.fieldStyle());
            final Question question = questionRepository.save(currentlyCreatedQuestion);
            questions.add(question);
        }
        return questions;
    }

    public UUID modifyQuestion(UUID publicId, QuestionDTO requestQuestion) {
        Question question = getQuestionByPublicId(publicId);
        return questionRepository.save(updateQuestionByRequest(requestQuestion, question)).getPublicId();
    }

    public void deleteQuestion(UUID publicId) {
        final Question question = getQuestionByPublicId(publicId);
        questionRepository.deleteById(question.getId());
    }

    public Question getQuestionByPublicId(UUID publicId) {
        return questionRepository.findQuestionByPublicId(publicId).orElseThrow(() -> new NotFoundException("There is no question with id: " + publicId));
    }

    private Question updateQuestionByRequest(QuestionDTO requestQuestion, Question question) {
        if (Objects.nonNull(requestQuestion.description())) question.setDescription(requestQuestion.description());
        if (Objects.nonNull(requestQuestion.fieldStyle())) question.setFieldStyle(requestQuestion.fieldStyle());
        return question;
    }

    private void isQuestionExistByDescription(String description) {
        if (questionRepository.existsByDescription(description))
            throw new BadRequestException("This question already exists: " + description);
    }
}
