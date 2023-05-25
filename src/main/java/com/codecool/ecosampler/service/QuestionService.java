package com.codecool.ecosampler.service;

import com.codecool.ecosampler.controller.dto.question.NewQuestion;
import com.codecool.ecosampler.controller.dto.question.QuestionDTO;
import com.codecool.ecosampler.domain.FieldStyle;
import com.codecool.ecosampler.domain.Question;
import com.codecool.ecosampler.exception.BadRequestException;
import com.codecool.ecosampler.exception.NotFoundException;
import com.codecool.ecosampler.repository.QuestionRepository;
import com.codecool.ecosampler.utilities.QuestionMapper;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@RequiredArgsConstructor
@Service
public class QuestionService {
    private final QuestionRepository questionRepository;

    public QuestionDTO createQuestion(NewQuestion newQuestion) {
        if (isQuestionExistByDescription(newQuestion.description())) {
            throw new BadRequestException(" Question already exists by description: " + newQuestion.description());
        }
        final Question question = questionRepository.save(
                new Question(UUID.randomUUID(),
                        newQuestion.description(),
                        newQuestion.fieldStyle()
                )
        );
        return QuestionMapper.toDTO(question);
    }

    public void createMultipleQuestionsWhichDoesntExist(List<NewQuestion> newQuestions) {
        newQuestions.stream()
                .filter(newQuestion -> !isQuestionExistByDescription(newQuestion.description()))
                .forEach(this::createQuestion);
    }

    protected List<Question> searchMultipleQuestions(List<NewQuestion> newQuestions) {
        return newQuestions.stream()
                .map(newQuestion -> questionRepository.findQuestionByDescription(newQuestion.description()))
                .filter(Optional::isPresent)
                .map(Optional::get)
                .toList();
    }

    public UUID modifyQuestion(UUID publicId, QuestionDTO requestQuestion) {
        Question question = getQuestionByPublicId(publicId);
        return questionRepository.
                save(updateQuestionByRequest(requestQuestion, question))
                .getPublicId();
    }

    public void deleteQuestion(UUID publicId) {
        final Question question = getQuestionByPublicId(publicId);
        questionRepository.deleteById(question.getId());
    }

    protected Question getQuestionByPublicId(UUID publicId) {
        return questionRepository.findQuestionByPublicId(publicId)
                .orElseThrow(() -> new NotFoundException("Question doesn't exist with Id: " + publicId));
    }

    private Question updateQuestionByRequest(QuestionDTO requestQuestion, Question question) {
        @NonNull String description = requestQuestion.description();
        question.setDescription(description);
        @NonNull FieldStyle fieldStyle = requestQuestion.fieldStyle();
        question.setFieldStyle(fieldStyle);
        return question;
    }

    private boolean isQuestionExistByDescription(String description) {
        return questionRepository.existsByDescription(description);
    }
}
