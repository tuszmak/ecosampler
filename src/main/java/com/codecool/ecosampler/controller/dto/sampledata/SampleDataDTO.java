package com.codecool.ecosampler.controller.dto.sampledata;

import java.time.LocalDateTime;
import java.util.UUID;

public record SampleDataDTO(UUID id, LocalDateTime time, UUID userID, UUID formID) {
}
