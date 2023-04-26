package com.codecool.ecosampler.repository;

import com.codecool.ecosampler.domain.Question;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface QuestionRepository extends JpaRepository<Question, Long> {
    boolean existsByDescription(String description);
}
