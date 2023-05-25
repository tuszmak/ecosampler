package com.codecool.ecosampler.service;

import com.codecool.ecosampler.controller.dto.sampledata.NewSampleData;
import com.codecool.ecosampler.controller.dto.sampledata.SampleDataDTO;
import com.codecool.ecosampler.domain.Form;
import com.codecool.ecosampler.domain.SampleData;
import com.codecool.ecosampler.domain.User;
import com.codecool.ecosampler.exeption.NotFoundException;
import com.codecool.ecosampler.repository.SampleDataRepository;
import com.codecool.ecosampler.utilities.SampleDataMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Service
public class SampleDataService {
    private final SampleDataRepository sampleDataRepository;
    private final UserService userService;
    private final FormService formService;
    private final AnswerService answerService;

    public List<SampleDataDTO> getAllSampleDataDTO() {
        return sampleDataRepository.findAll().stream()
                .map(SampleDataMapper::toDTO)
                .collect(Collectors.toList());
    }

    public SampleDataDTO getSampleDataDTOByPublicId(UUID publicId) {
        SampleData sampleData = getSampleDataByPublicId(publicId);
        return SampleDataMapper.toDTO(sampleData);
    }

    public SampleDataDTO createSampleData(NewSampleData newSampleData) {

        User user = userService.getUserByPublicId(newSampleData.userID());
        Form form = formService.getFormByPublicId(newSampleData.formID());

        SampleData sampleData = sampleDataRepository.save(new SampleData(UUID.randomUUID(),
                LocalDateTime.now(),
                user,
                form
                )
        );
        answerService.createListOfAnswers(newSampleData.newAnswers(), sampleData);

        return SampleDataMapper.toDTO(sampleData);
    }

    public void deleteSampleData(UUID publicId) {
        final SampleData sampleData = getSampleDataByPublicId(publicId);
        sampleDataRepository.deleteById(sampleData.getId());
    }

    protected SampleData getSampleDataByPublicId(UUID publicId) {
        return sampleDataRepository.
                findSampleDataByPublicId(publicId)
                .orElseThrow(() -> new NotFoundException("Can't find data with this id: " + publicId));
    }
}
