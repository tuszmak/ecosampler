package com.codecool.ecosampler.utilities;

import com.codecool.ecosampler.controller.dto.project.ProjectDTO;
import com.codecool.ecosampler.domain.Project;
import org.springframework.stereotype.Service;

@Service
public class ProjectMapper {

    public ProjectDTO toDTO(Project project) {
        return new ProjectDTO(project.getPublicId(), project.getName(), project.getDescription());
    }
}
