package com.codecool.ecosampler.service;

import com.codecool.ecosampler.domain.Question;
import com.codecool.ecosampler.exeption.BadRequestException;
import com.codecool.ecosampler.exeption.NotFoundException;
import com.codecool.ecosampler.repository.QuestionRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@AllArgsConstructor
@Service
public class QuestionService {
    private QuestionRepository questionRepository;

    public List<Question> getAllQuestions() {
        return questionRepository.findAll();
    }

    public Long createQuestion(Question question) {
        if (questionRepository.existsByDescription(question.getDescription()))
            throw new BadRequestException("This question already exists: " + question.getDescription());
        return questionRepository.save(question).getId();
    }

    public void deleteQuestion(Long id) {
        if (questionRepository.existsById(id))
            questionRepository.deleteById(id);
        else
            throw new NotFoundException("There is no question with id: " + id);
    }
}
