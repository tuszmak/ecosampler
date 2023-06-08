package com.codecool.ecosampler.utilities;

import com.codecool.ecosampler.controller.dto.sampledata.SampleDataDTO;
import com.codecool.ecosampler.domain.Form;
import com.codecool.ecosampler.domain.Role;
import com.codecool.ecosampler.domain.SampleData;
import com.codecool.ecosampler.domain.User;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertEquals;

class SampleDataMapperTest {
    private UUID uuid;
    private LocalDateTime localDateTime;
    private User user;
    @BeforeEach
    public void setup(){
        uuid = UUID.randomUUID();
        localDateTime = LocalDateTime.now();
        user = new User(uuid,"test","test@test.cx", Role.SCIENTIST,"");
    }
    @Test
    void should_return_dto() {
        SampleData sampleData = new SampleData(uuid,
                localDateTime, user, new Form());
        SampleDataDTO expected = new SampleDataDTO(uuid,localDateTime,uuid,null);
        SampleDataDTO actual = SampleDataMapper.toDTO(sampleData);
        assertEquals(expected,actual);
    }
}