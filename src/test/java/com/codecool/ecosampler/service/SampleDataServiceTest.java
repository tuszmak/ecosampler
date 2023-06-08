package com.codecool.ecosampler.service;

import com.codecool.ecosampler.repository.SampleDataRepository;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
class SampleDataServiceTest {
    @Mock
    private SampleDataRepository sampleDataRepository;
    @Mock
    private UserService userService;
    @Mock
    private FormService formService;
    @Mock
    private AnswerService answerService;
    @InjectMocks
    private SampleDataService sampleDataService;


}