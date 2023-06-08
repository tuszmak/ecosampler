package com.codecool.ecosampler.controller.dto.project;

import java.util.List;
import java.util.UUID;

public record NewProject(String name, String description, List<UUID> userIDs) {
}
