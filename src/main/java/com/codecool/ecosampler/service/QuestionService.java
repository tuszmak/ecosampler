package com.codecool.ecosampler.service;

import com.codecool.ecosampler.domain.Question;
import com.codecool.ecosampler.exeption.BadRequestException;
import com.codecool.ecosampler.exeption.NotFoundException;
import com.codecool.ecosampler.repository.QuestionRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;

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

    public Question modifyQuestion(Long id, Question requestQuestion) {
        Question question = questionRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("There is no question with id: " + id));

        return questionRepository.save(
                updateQuestionByRequest(requestQuestion, question)
        );
    }

    private Question updateQuestionByRequest(Question requestQuestion, Question question) {
        if (!requestQuestion.getDescription().isEmpty())
            question.setDescription(requestQuestion.getDescription());
        if (Objects.nonNull(requestQuestion.getFieldStyle()))
            question.setFieldStyle(requestQuestion.getFieldStyle());
        return question;
    }

    public void deleteQuestion(Long id) {
        if (questionRepository.existsById(id))
            questionRepository.deleteById(id);
        else
            throw new NotFoundException("There is no question with id: " + id);
    }
}
