package com.codecool.ecosampler.repository;

import com.codecool.ecosampler.domain.Question;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface QuestionRepository extends JpaRepository<Question, Long> {
    boolean existsByDescription(String description);

    Optional<Question> findQuestionByPublicId(UUID publicId);
    Optional<Question> findQuestionByDescription(String description);
}
