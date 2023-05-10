package com.codecool.ecosampler.service;

import com.codecool.ecosampler.controller.dto.project.NewProject;
import com.codecool.ecosampler.controller.dto.project.ProjectAndUserId;
import com.codecool.ecosampler.controller.dto.project.ProjectDTO;
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
    private final ProjectRepository projectRepository;
    private final UserService userService;
    private final ProjectMapper projectMapper;

    public List<ProjectDTO> getAllProjectDTO() {
        return projectRepository.findAll().stream()
                .map(projectMapper::toDTO)
                .collect(Collectors.toList());
    }

    public ProjectDTO addNewProject(NewProject newProject) {
        checkIfProjectNameExists(newProject.name());
        final Project project = projectRepository.save(new Project(UUID.randomUUID(),
                        newProject.name(),
                        newProject.description()
                )
        );
        return projectMapper.toDTO(project);
    }

    public List<ProjectDTO> getProjectsDTOByUserPublicId(UUID userPublicId) {
        User user = userService.getUserByPublicId(userPublicId);
        return projectRepository.findAllProjectByUserId(user.getId()).stream()
                .map(projectMapper::toDTO)
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

    public void addUserToProject(ProjectAndUserId projectAndUserId) {
        User user = userService.getUserByPublicId(projectAndUserId.userID());
        Project project = getProjectByPublicId(projectAndUserId.projectID());
        project.addUserToProject(user);
        projectRepository.save(project);
    }

    public Project getProjectByPublicId(UUID publicId) {
        return projectRepository.findProjectByPublicId(publicId)
                .orElseThrow(() -> new NotFoundException("Project not exist with Id: " + publicId));
    }

    private Project updateProjectWithRequest(ProjectDTO requestProject, Project project) {
        if (Objects.nonNull(requestProject.name()))
            project.setName(requestProject.name());

        if (Objects.nonNull(requestProject.description()))
            project.setDescription(requestProject.description());
        // TODO The rest
        return project;
    }

    private void checkIfProjectNameExists(String name) {
        if (projectRepository.existsByName(name))
            throw new BadRequestException("Project is already exist with name: " + name);
    }
}
