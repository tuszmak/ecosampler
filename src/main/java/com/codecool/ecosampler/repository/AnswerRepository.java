package com.codecool.ecosampler.repository;

import com.codecool.ecosampler.domain.Answer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface AnswerRepository extends JpaRepository<Answer, Long> {
    Optional<Answer> findAnswerByPublicId(UUID publicId);
}
