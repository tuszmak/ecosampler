package com.codecool.ecosampler.service;

import com.codecool.ecosampler.controller.dto.answer.AnswerDTO;
import com.codecool.ecosampler.controller.dto.answer.NewAnswer;
import com.codecool.ecosampler.domain.Answer;
import com.codecool.ecosampler.domain.Question;
import com.codecool.ecosampler.exeption.NotFoundException;
import com.codecool.ecosampler.repository.AnswerRepository;
import com.codecool.ecosampler.utilities.AnswerMapper;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;
import java.util.UUID;
import java.util.stream.Collectors;

@AllArgsConstructor
@Service
public class AnswerService {
    private final AnswerRepository answerRepository;
    private final QuestionService questionService;

    public List<AnswerDTO> getAllAnswersDTO() {
        return answerRepository.findAll().stream()
                .map(AnswerMapper::toDto)
                .collect(Collectors.toList());
    }

    protected void createListOfAnswers(List<NewAnswer> newAnswers, SampleData sampleData) {
        answerRepository.saveAll(
                newAnswers.stream()
                        .map(newAnswer -> new Answer(
                                        UUID.randomUUID(),
                                        newAnswer.answer(),
                                        questionService.getQuestionByPublicId(newAnswer.questionID()),
                                        sampleData
                                )
                        )
                        .toList()
        );
    }

    public UUID modifyAnswer(UUID publicId, AnswerDTO requestAnswer) {
        Answer answer = getAnswerByPublicId(publicId);
        return answerRepository.save(
                        updateAnswerByRequest(requestAnswer, answer)
                )
                .getPublicId();
    }

    public void deleteAnswer(UUID publicId) {
        final Answer answer = getAnswerByPublicId(publicId);
        answerRepository.deleteById(answer.getId());
    }

    protected Answer getAnswerByPublicId(UUID publicId) {
        return answerRepository.findAnswerByPublicId(publicId)
                .orElseThrow(() -> new NotFoundException("There is no answer with id: " + publicId));
    }

    private Answer updateAnswerByRequest(AnswerDTO requestAnswer, Answer answer) {
        if (Objects.nonNull(requestAnswer.answer()))
            answer.setAnswer(requestAnswer.answer());
        return answer;
    }
}
