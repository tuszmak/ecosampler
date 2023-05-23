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

import java.util.*;
import java.util.stream.Collectors;

@AllArgsConstructor
@Service
public class QuestionService {
    private final QuestionRepository questionRepository;

    public List<QuestionDTO> getAllQuestionsDTO() {
        return questionRepository.findAll().stream()
                .map(QuestionMapper::toDTO)
                .collect(Collectors.toList());
    }

    public QuestionDTO createQuestion(NewQuestion newQuestion) {
        if(isQuestionExistByDescription(newQuestion.description())){
            throw new BadRequestException("This question already exists: " + newQuestion.description());
        }
        final Question question = questionRepository.save(new Question(UUID.randomUUID(),
                        newQuestion.description(),
                        newQuestion.fieldStyle()
                )
        );
        return QuestionMapper.toDTO(question);
    }

    protected List<Question> createMultipleQuestions(List<NewQuestion> newQuestions) {
        List<Question> questions = new ArrayList<>();
        for (NewQuestion newQuestion : newQuestions) {
            if(!isQuestionExistByDescription(newQuestion.description())){
                Question currentlyCreatedQuestion = new Question(
                        UUID.randomUUID(),
                        newQuestion.description(),
                        newQuestion.fieldStyle());
                final Question question = questionRepository.save(currentlyCreatedQuestion);
                questions.add(question);
            }
            else {
                Optional<Question> question = questionRepository.findQuestionByDescription(newQuestion.description());
                question.ifPresent(questions::add);
            }
        }
        return questions;
    }

    public UUID modifyQuestion(UUID publicId, QuestionDTO requestQuestion) {
        Question question = getQuestionByPublicId(publicId);
        return questionRepository.
                save(
                        updateQuestionByRequest(requestQuestion, question)
        )
                .getPublicId();
    }

    public void deleteQuestion(UUID publicId) {
        final Question question = getQuestionByPublicId(publicId);
        questionRepository.deleteById(question.getId());
    }

    protected Question getQuestionByPublicId(UUID publicId) {
        return questionRepository.findQuestionByPublicId(publicId)
                .orElseThrow(() -> new NotFoundException("There is no question with id: " + publicId));
    }

    private Question updateQuestionByRequest(QuestionDTO requestQuestion, Question question) {
        if (Objects.nonNull(requestQuestion.description())) question.setDescription(requestQuestion.description());
        if (Objects.nonNull(requestQuestion.fieldStyle())) question.setFieldStyle(requestQuestion.fieldStyle());
        return question;
    }

    private boolean isQuestionExistByDescription(String description) {
        return questionRepository.existsByDescription(description);
    }
}
