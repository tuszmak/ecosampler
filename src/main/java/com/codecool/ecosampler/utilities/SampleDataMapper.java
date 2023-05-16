package com.codecool.ecosampler.utilities;

import com.codecool.ecosampler.controller.dto.sampledata.SampleDataDTO;
import com.codecool.ecosampler.domain.SampleData;

public class SampleDataMapper {
    public static SampleDataDTO toDTO(SampleData sampleData) {
        return new SampleDataDTO(sampleData.getPublicId(),
                sampleData.getTime(),
                sampleData.getUser().getPublicId(),
                sampleData.getForm().getPublicId()
        );
    }
}
