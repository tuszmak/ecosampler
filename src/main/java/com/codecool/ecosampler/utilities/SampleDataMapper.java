package com.codecool.ecosampler.utilities;

import com.codecool.ecosampler.controller.dto.sampledata.SampleDataDTO;
import com.codecool.ecosampler.domain.SampleData;
import org.springframework.stereotype.Service;

@Service
public class SampleDataMapper {
    public SampleDataDTO toDTO(SampleData sampleData) {
        return new SampleDataDTO(sampleData.getPublicId(),
                sampleData.getTime(),
                sampleData.getUser().getPublicId(),
                sampleData.getForm().getPublicId()
        );
    }
}
