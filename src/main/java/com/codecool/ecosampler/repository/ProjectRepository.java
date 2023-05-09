package com.codecool.ecosampler.repository;

import com.codecool.ecosampler.domain.Project;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository
public interface ProjectRepository extends JpaRepository<Project, Long> {
    @Query("SELECT p FROM project p JOIN p.users u WHERE u.id = :userId")
    List<Project> findAllProjectByUserId(@Param("userId") Long userId);
    Optional<Project> findProjectByPublicId(UUID publicId);
}
