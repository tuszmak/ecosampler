package com.codecool.ecosampler.repository;

import com.codecool.ecosampler.domain.SampleData;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SampleDataRepository extends JpaRepository<SampleData, Long> {
}
