package com.codecool.ecosampler.controller;

import com.codecool.ecosampler.controller.dto.sampledata.NewSampleData;
import com.codecool.ecosampler.controller.dto.sampledata.SampleDataDTO;
import com.codecool.ecosampler.service.SampleDataService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@AllArgsConstructor
@RestController
@RequestMapping("/api/v1/sampledata")
public class SampleDataController {
    SampleDataService sampleDataService;
    //TODO: create get sampleData by user and get sampleData by project endpoints

    @GetMapping("/{publicId}")
    public SampleDataDTO getSampleDataByPublicId(@PathVariable UUID publicId) {
        return sampleDataService.getSampleDataDTOByPublicId(publicId);
    }

    @PostMapping
    @ResponseStatus(value = HttpStatus.CREATED)
    public SampleDataDTO createQuestion(@RequestBody NewSampleData sampleData) {
        return sampleDataService.createSampleData(sampleData);
    }

    @DeleteMapping("/{publicId}")
    public void deleteSampleData(@PathVariable UUID publicId) {
        sampleDataService.deleteSampleData(publicId);
    }
}
