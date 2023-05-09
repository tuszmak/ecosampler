package com.codecool.ecosampler.service;

import com.codecool.ecosampler.controller.dto.sampledata.NewSampleData;
import com.codecool.ecosampler.controller.dto.sampledata.SampleDataDTO;
import com.codecool.ecosampler.domain.Form;
import com.codecool.ecosampler.domain.SampleData;
import com.codecool.ecosampler.domain.User;
import com.codecool.ecosampler.exeption.NotFoundException;
import com.codecool.ecosampler.repository.SampleDataRepository;
import com.codecool.ecosampler.utilities.SampleDataMapper;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@AllArgsConstructor
@Service
public class SampleDataService {
    private final SampleDataRepository sampleDataRepository;
    private final SampleDataMapper sampleDataMapper;
    private final UserService userService;
    private final FormService formService;


    public List<SampleDataDTO> getAllSampleDataDTO() {
        return sampleDataRepository.findAll().stream()
                .map(sampleDataMapper::toDTO)
                .collect(Collectors.toList());
    }

    public SampleDataDTO getSampleDataDTOByPublicId(UUID publicId) {
        SampleData sampleData = getSampleDataByPublicId(publicId);
        return sampleDataMapper.toDTO(sampleData);
    }

    public UUID createSampleData(NewSampleData sampleData) {
        User user = userService.getUserByPublicId(sampleData.userID());
        Form form = formService.getFormByPublicId(sampleData.formID());
        return sampleDataRepository.save(new SampleData(UUID.randomUUID(),
                                LocalDateTime.now(),
                                user,
                                form
                        )
                )
                .getPublicId();
    }

    public void deleteSampleData(UUID publicId) {
        final SampleData sampleData = getSampleDataByPublicId(publicId);
        sampleDataRepository.deleteById(sampleData.getId());
    }

    public SampleData getSampleDataByPublicId(UUID publicId) {
        return sampleDataRepository.
                findSampleDataByPublicId(publicId)
                .orElseThrow(() -> new NotFoundException("Can't find data with this id: " + publicId));
    }
}
