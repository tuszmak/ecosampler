package com.codecool.ecosampler.repository;

import com.codecool.ecosampler.domain.Form;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository
public interface FormRepository extends JpaRepository<Form, Long> {
    Optional<Form> findFormByPublicId(UUID publicId);
    @Query("SELECT f FROM project p JOIN p.formList f WHERE p.publicId = :projectId")
    List<Form> findFormsByProjectID(UUID projectId);
}
