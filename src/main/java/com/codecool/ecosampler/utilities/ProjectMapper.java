package com.codecool.ecosampler.utilities;

import com.codecool.ecosampler.controller.dto.project.ProjectDTO;
import com.codecool.ecosampler.domain.Project;

public class ProjectMapper {

    public static ProjectDTO toDTO(Project project) {
        return new ProjectDTO(project.getPublicId(), project.getName(), project.getDescription());
    }
}
