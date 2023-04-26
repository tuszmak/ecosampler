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

    public Question modifyQuestion(Long id, Question newQuestion) {
        if (isValidQuestion(newQuestion)) {
            Question question = questionRepository.findById(id)
                    .orElseThrow(() -> new NotFoundException("There is no question with id: " + id));
            setQuestionProperties(newQuestion, question);
            return question;
        }
        throw new BadRequestException("No changes requested!");
    }

    private boolean isValidQuestion(Question newQuestion) {
        return Objects.nonNull(newQuestion.getFieldStyle()) || Objects.nonNull(newQuestion.getDescription());
    }

    private void setQuestionProperties(Question newQuestion, Question question) {
        if (Objects.nonNull(newQuestion.getDescription()))
            question.setDescription(newQuestion.getDescription());
        if (Objects.nonNull(newQuestion.getFieldStyle()))
            question.setFieldStyle(newQuestion.getFieldStyle());
    }

    public void deleteQuestion(Long id) {
        if (questionRepository.existsById(id))
            questionRepository.deleteById(id);
        else
            throw new NotFoundException("There is no question with id: " + id);
    }
}
