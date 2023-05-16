package com.codecool.ecosampler.controller.dto.project;

import java.util.List;
import java.util.UUID;

public record ProjectModifyUsers(UUID projectID, List<UUID> addUserIDs, List<UUID> removeUserIDs) {
}
