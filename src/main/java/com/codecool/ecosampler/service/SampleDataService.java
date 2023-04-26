package com.codecool.ecosampler.service;

import com.codecool.ecosampler.domain.SampleData;
import com.codecool.ecosampler.repository.SampleDataRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@AllArgsConstructor
@Service
public class SampleDataService {
    SampleDataRepository sampleDataRepository;

    public List<SampleData> getAllSampleData() {
        return sampleDataRepository.findAll();
    }

    public Long createSampleData(SampleData sampleData) {
        return sampleDataRepository.save(sampleData).getId();
    }

    public void deleteSampleData(Long id) {
        sampleDataRepository.deleteById(id);
    }
}
