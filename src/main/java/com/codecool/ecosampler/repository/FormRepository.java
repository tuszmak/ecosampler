package com.codecool.ecosampler.repository;

import com.codecool.ecosampler.domain.Form;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface FormRepository extends JpaRepository<Form, Long> {
    Optional<Form> findFormByPublicId(UUID publicId);
}
