package com.codecool.ecosampler.controller;

import com.codecool.ecosampler.domain.SampleData;
import com.codecool.ecosampler.service.SampleDataService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@AllArgsConstructor
@RestController
@RequestMapping("/api/v1/sampledata")
public class SampleDataController {
    SampleDataService sampleDataService;

    @GetMapping("")
    public List<SampleData> getAllSampleData() {
        return sampleDataService.getAllSampleData();
    }

    @ResponseStatus(value = HttpStatus.CREATED)
    @PostMapping
    public Long createQuestion(@RequestBody SampleData sampleData) {
        return sampleDataService.createSampleData(sampleData);
    }

    @DeleteMapping("/{id}")
    public void deleteSampleData(@PathVariable Long id) {
        sampleDataService.deleteSampleData(id);
    }

}
