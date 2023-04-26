package com.codecool.ecosampler.service;

import com.codecool.ecosampler.controller.dto.sampledata.NewSampleData;
import com.codecool.ecosampler.domain.Form;
import com.codecool.ecosampler.domain.SampleData;
import com.codecool.ecosampler.domain.User;
import com.codecool.ecosampler.exeption.NotFoundException;
import com.codecool.ecosampler.repository.FormRepository;
import com.codecool.ecosampler.repository.SampleDataRepository;
import com.codecool.ecosampler.repository.UserRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@AllArgsConstructor
@Service
public class SampleDataService {
    SampleDataRepository sampleDataRepository;
    UserRepository userRepository;
    FormRepository formRepository;

    public List<SampleData> getAllSampleData() {
        return sampleDataRepository.findAll();
    }

    public SampleData getSpecificSampleData(Long id) {
        return sampleDataRepository.
                findById(id).orElseThrow(() -> new NotFoundException("Can't find data with this id: " + id));
    }

    public Long createSampleData(NewSampleData sampleData) {
        User user = userRepository.findById(sampleData.userID())
                .orElseThrow(() -> new NotFoundException("Invalid user ID"));
        Form form = formRepository.findById(sampleData.formID())
                .orElseThrow(() -> new NotFoundException("Invalid form ID"));
        return sampleDataRepository.save(new SampleData(user,form)).getId();
    }

    public void deleteSampleData(Long id) {
        sampleDataRepository.deleteById(id);
    }
}
