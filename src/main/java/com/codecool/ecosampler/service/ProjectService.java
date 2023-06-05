package com.codecool.ecosampler.service;

import com.codecool.ecosampler.controller.dto.form.NewForm;
import com.codecool.ecosampler.controller.dto.project.ModifyUsersOnProject;
import com.codecool.ecosampler.controller.dto.project.NewProject;
import com.codecool.ecosampler.controller.dto.project.ProjectDTO;
import com.codecool.ecosampler.controller.dto.user.UserForSelectDTO;
import com.codecool.ecosampler.domain.Form;
import com.codecool.ecosampler.domain.Project;
import com.codecool.ecosampler.domain.User;
import com.codecool.ecosampler.exception.BadRequestException;
import com.codecool.ecosampler.exception.NotFoundException;
import com.codecool.ecosampler.repository.ProjectRepository;
import com.codecool.ecosampler.utilities.ProjectMapper;
import com.codecool.ecosampler.utilities.UserMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;
import java.util.UUID;

@RequiredArgsConstructor
@Service
public class ProjectService {
    private final ProjectRepository projectRepository;
    private final FormService formService;
    private final UserService userService;

    public List<ProjectDTO> getAllProjectDTO() {
        return projectRepository.findAll()
                .stream()
                .map(ProjectMapper::toDTO)
                .toList();
    }

    public ProjectDTO addNewProject(NewProject newProject) {
        checkIfProjectNameExists(newProject.name());
        Project project = new Project(
                UUID.randomUUID(),
                newProject.name(),
                newProject.description()
        );
        if (Objects.nonNull(newProject.userIDs())) {
            List<User> users = userService.getUsersByPublicId(newProject.userIDs());
            project.addUsersToProject(users);
        }
        project = projectRepository.save(project);
        return ProjectMapper.toDTO(project);
    }

    public List<ProjectDTO> getProjectsDTOByUserPublicId(UUID userPublicId) {
        User user = userService.getUserByPublicId(userPublicId);
        return projectRepository.findAllProjectByUserId(user.getId())
                .stream()
                .map(ProjectMapper::toDTO)
                .toList();
    }

    public void deleteProject(UUID publicId) {
        projectRepository.findProjectByPublicId(publicId)
                .ifPresentOrElse(project -> projectRepository.deleteById(project.getId()), () -> {
                    throw new NotFoundException("Project doesn't exist with Id: " + publicId);
                });
    }

    public ProjectDTO updateProject(UUID publicId, ProjectDTO requestProject) {
        Project project = getProjectByPublicId(publicId);
        Project updatedProject = projectRepository.save(updateProjectWithRequest(requestProject, project));
        return ProjectMapper.toDTO(updatedProject);
    }

    public void modifyUsersOnProject(ModifyUsersOnProject modifyUsersOnProject, UUID projectID) {
        Project project = getProjectByPublicId(projectID);
        if (Objects.nonNull(modifyUsersOnProject.addUserIDs())) {
            List<User> addUsers = userService.getUsersByPublicId(modifyUsersOnProject.addUserIDs());
            project.addUsersToProject(addUsers);
        }
        if (Objects.nonNull(modifyUsersOnProject.removeUserIDs())) {
            List<User> removeUsers = userService.getUsersByPublicId(modifyUsersOnProject.removeUserIDs());
            project.removeUsersFromProject(removeUsers);
        }
        projectRepository.save(project);
    }

    protected Project getProjectByPublicId(UUID publicId) {
        return projectRepository.findProjectByPublicId(publicId)
                .orElseThrow(() -> new NotFoundException("Project doesn't exist with Id: " + publicId));
    }

    protected Project updateProjectWithRequest(ProjectDTO requestProject, Project project) {
        if (Objects.nonNull(requestProject.name())) project.setName(requestProject.name());
        if (Objects.nonNull(requestProject.description())) project.setDescription(requestProject.description());
        return project;
    }

    private void checkIfProjectNameExists(String name) {
        if (projectRepository.existsByName(name))
            throw new BadRequestException("Project already exists by name: " + name);
    }

    public void addFormToProject(NewForm newForm, UUID projectID) {
        Project project = getProjectByPublicId(projectID);
        Form form = formService.createNewForm(newForm);
        project.addFormToProject(form);
        projectRepository.save(project);

    }

    public List<UserForSelectDTO> getUserForSelectDTOForProjectByPublicId(UUID publicProjectId) {
        return getUserForProjectByPublicId(publicProjectId).stream()
                .map(UserMapper::toUserForSelectorDTO)
                .toList();
    }

    protected List<User> getUserForProjectByPublicId(UUID publicProjectId) {
        return projectRepository.findUsersByProjectPublicId(publicProjectId);
    }
}
