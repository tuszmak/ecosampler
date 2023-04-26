package com.codecool.ecosampler.service;

import com.codecool.ecosampler.domain.Answer;
import com.codecool.ecosampler.exeption.NotFoundException;
import com.codecool.ecosampler.repository.AnswerRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;

@AllArgsConstructor
@Service
public class AnswerService {

    private AnswerRepository answerRepository;

    public List<Answer> getAllAnswers() {
        return answerRepository.findAll();
    }

    public Long createAnswer(Answer answer) {
        return answerRepository.save(answer)
                .getId();
    }

    public Answer modifyAnswer(Long id, Answer requestAnswer) {
        Answer answer = answerRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("There is no answer with id: " + id));

        return answerRepository.save(
                updateAnswerByRequest(requestAnswer, answer)
        );
    }

    private Answer updateAnswerByRequest(Answer requestAnswer, Answer answer) {
        if (Objects.nonNull(requestAnswer.getAnswer()))
            answer.setAnswer(requestAnswer.getAnswer());
        return answer;
    }

    public void deleteAnswer(Long id) {
        if (answerRepository.existsById(id))
            answerRepository.deleteById(id);
    throw new NotFoundException("There is no answer with id: " + id);
    }
}
