package com.codecool.ecosampler.service;

import com.codecool.ecosampler.controller.dto.ProjectDTO;
import com.codecool.ecosampler.controller.dto.project.NewProject;
import com.codecool.ecosampler.controller.dto.project.ProjectAndUserId;
import com.codecool.ecosampler.domain.Project;
import com.codecool.ecosampler.domain.User;
import com.codecool.ecosampler.exeption.BadRequestException;
import com.codecool.ecosampler.exeption.NotFoundException;
import com.codecool.ecosampler.repository.ProjectRepository;
import com.codecool.ecosampler.utilities.ProjectMapper;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;
import java.util.UUID;
import java.util.stream.Collectors;

@AllArgsConstructor
@Service
public class ProjectService {
    private ProjectRepository projectRepository;
    public UserService userService;
    private ProjectMapper projectMapper;

    public List<ProjectDTO> getAllProjectDTO() {
        return projectRepository.findAll().stream()
                .map(project -> projectMapper.toDTO(project))
                .collect(Collectors.toList());
    }

    public UUID addNewProject(NewProject project) {
        try {
            return projectRepository.save(new Project(UUID.randomUUID(),
                                    project.name(),
                                    project.description()
                            )
                    )
                    .getPublicId();
        } catch (Exception e) {
            throw new BadRequestException("There some problem with current data.");
        }
    }

    public List<ProjectDTO> getProjectsDTOByUserPublicId(UUID userPublicId) {
        User user = userService.getUserByPublicId(userPublicId);
        return projectRepository.findAllProjectByUserId(user.getId()).stream()
                .map(project -> projectMapper.toDTO(project))
                .collect(Collectors.toList());
    }

    public void deleteProject(UUID publicId) {
        projectRepository.findProjectByPublicId(publicId)
                .ifPresentOrElse(
                        project -> projectRepository.deleteById(project.getId()),
                        () -> {
                            throw new NotFoundException("Project not exist with Id: " + publicId);
                        }
                );
    }

    public Project updateProject(UUID publicId, ProjectDTO requestProject) {
        Project project = getProjectByPublicId(publicId);
        return projectRepository.save(
                updateProjectWithRequest(requestProject, project)
        );
    }

    private Project updateProjectWithRequest(ProjectDTO requestProject, Project project) {
        if (Objects.nonNull(requestProject.name()))
            project.setName(requestProject.name());

        if (Objects.nonNull(requestProject.description()))
            project.setDescription(requestProject.description());
        // TODO The rest
        return project;
    }

    public void addUserToProject(ProjectAndUserId projectAndUserId) {
        User user = userService.getUserByPublicId(projectAndUserId.userID());
        Project project = getProjectByPublicId(projectAndUserId.projectID());
        project.addUserToProject(user);
        projectRepository.save(project);
    }

    private Project getProjectByPublicId(UUID publicId) {
        return projectRepository.findProjectByPublicId(publicId)
                .orElseThrow(() -> new NotFoundException("Project not exist with Id: " + publicId));
    }
}
