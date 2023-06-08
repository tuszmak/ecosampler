package com.codecool.ecosampler.service;

import com.codecool.ecosampler.controller.dto.sampledata.NewSampleData;
import com.codecool.ecosampler.controller.dto.sampledata.SampleDataDTO;
import com.codecool.ecosampler.domain.Form;
import com.codecool.ecosampler.domain.Role;
import com.codecool.ecosampler.domain.SampleData;
import com.codecool.ecosampler.domain.User;
import com.codecool.ecosampler.exception.NotFoundException;
import com.codecool.ecosampler.repository.SampleDataRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Optional;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class SampleDataServiceTest {
    private UUID uuid;
    private LocalDateTime localDateTime;
    private User user;

    @BeforeEach
    public void setup() {
        uuid = UUID.randomUUID();
        localDateTime = LocalDateTime.now();
        user = new User(uuid, "test", "test@test.cx", Role.SCIENTIST, "");
    }

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

    //getSampleDataDTOByPublicId tests
    @Test
    void should_return_no_sampledata_dto() {
        when(sampleDataRepository.findSampleDataByPublicId(any(UUID.class))).thenReturn(Optional.empty());
        assertThrows(NotFoundException.class, () -> sampleDataService.getSampleDataDTOByPublicId(uuid));
    }

    @Test
    void should_return_a_sampledata_dto() {
        SampleData sampleData = new SampleData(uuid, localDateTime, user, new Form());
        when(sampleDataRepository.findSampleDataByPublicId(any(UUID.class))).thenReturn(Optional.of(sampleData));
        SampleDataDTO expected = new SampleDataDTO(uuid, localDateTime, uuid, null);
        SampleDataDTO actual = sampleDataService.getSampleDataDTOByPublicId(uuid);
        assertEquals(expected, actual);
        verify(sampleDataRepository).findSampleDataByPublicId(any());
    }

    @Test
    void should_create_new_sampledata() {
        NewSampleData newSampleData = new NewSampleData(uuid, uuid, new ArrayList<>());
        SampleData sampleData = new SampleData(uuid, localDateTime, user, new Form());
        when(userService.getUserByPublicId(any(UUID.class))).thenReturn(user);
        when(formService.getFormByPublicId(any(UUID.class))).thenReturn(null);
        when(sampleDataRepository.save(any(SampleData.class))).thenReturn(sampleData);
//        doNothing().when(answerService).createListOfAnswers(anyList(), any(SampleData.class));
        SampleDataDTO expected = new SampleDataDTO(uuid, localDateTime, user.getPublicId(), null);
        SampleDataDTO actual = sampleDataService.createSampleData(newSampleData);
        assertEquals(expected, actual);
        verify(userService).getUserByPublicId(any(UUID.class));
        verify(formService).getFormByPublicId(any(UUID.class));
        verify(answerService).createListOfAnswers(anyList(),any());
    }
    @Test
    void should_delete_sampledata(){
        SampleData sampleData = new SampleData(2L,uuid,
                localDateTime, user, new Form(),new ArrayList<>());
        when(sampleDataRepository
                .findSampleDataByPublicId(any(UUID.class))).thenReturn(Optional.of(sampleData));
        sampleDataService.deleteSampleData(uuid);
        verify(sampleDataRepository).deleteById(anyLong());
    }
}