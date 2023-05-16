package com.codecool.ecosampler.service;

import com.codecool.ecosampler.controller.dto.form.NewForm;
import com.codecool.ecosampler.controller.dto.project.ModifyUsersOnProject;
import com.codecool.ecosampler.controller.dto.project.NewProject;
import com.codecool.ecosampler.controller.dto.project.ProjectDTO;
import com.codecool.ecosampler.controller.dto.user.UserForSelectDTO;
import com.codecool.ecosampler.domain.Form;
import com.codecool.ecosampler.domain.Project;
import com.codecool.ecosampler.domain.User;
import com.codecool.ecosampler.exeption.BadRequestException;
import com.codecool.ecosampler.exeption.NotFoundException;
import com.codecool.ecosampler.repository.ProjectRepository;
import com.codecool.ecosampler.utilities.Mapper;
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
    private final FormService formService;
    private final UserService userService;
    private final ProjectMapper projectMapper;

    public List<ProjectDTO> getAllProjectDTO() {
        return projectRepository.findAll().stream().map(projectMapper::toDTO).collect(Collectors.toList());
    }

    public ProjectDTO addNewProject(NewProject newProject) {
        checkIfProjectNameExists(newProject.name());
        Project project = new Project(UUID.randomUUID(), newProject.name(), newProject.description());
        if (Objects.nonNull(newProject.userIDs())) {
            System.out.println("addusers");
            List<User> users = userService.getUsersByPublicId(newProject.userIDs());
            project.addUsersToProject(users);
        }
        project = projectRepository.save(project);
        return projectMapper.toDTO(project);
    }

    public List<ProjectDTO> getProjectsDTOByUserPublicId(UUID userPublicId) {
        User user = userService.getUserByPublicId(userPublicId);
        return projectRepository.findAllProjectByUserId(user.getId()).stream().map(projectMapper::toDTO).collect(Collectors.toList());
    }

    public void deleteProject(UUID publicId) {
        projectRepository.findProjectByPublicId(publicId).ifPresentOrElse(project -> projectRepository.deleteById(project.getId()), () -> {
            throw new NotFoundException("Project not exist with Id: " + publicId);
        });
    }

    public ProjectDTO updateProject(UUID publicId, ProjectDTO requestProject) {
        Project project = getProjectByPublicId(publicId);
        Project updatedProject = projectRepository.save(updateProjectWithRequest(requestProject, project));
        return projectMapper.toDTO(updatedProject);
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
                .orElseThrow(() -> new NotFoundException("Project not exist with Id: " + publicId));
    }

    protected Project updateProjectWithRequest(ProjectDTO requestProject, Project project) {
        if (Objects.nonNull(requestProject.name())) project.setName(requestProject.name());

        if (Objects.nonNull(requestProject.description())) project.setDescription(requestProject.description());
        // TODO The rest
        return project;
    }

    private void checkIfProjectNameExists(String name) {
        if (projectRepository.existsByName(name))
            throw new BadRequestException("Project is already exist with name: " + name);
    }

    public void addFormToProject(NewForm newForm, UUID projectID) {
        Project project = getProjectByPublicId(projectID);
        Form form = formService.createNewForm(newForm);
        project.addFormToProject(form);
        projectRepository.save(project);

    }

    public List<UserForSelectDTO> getUserForSelectDTOForProjectByPublicId(UUID publicProjectId) {
        return getUserForProjectByPublicId(publicProjectId).stream()
                .map(Mapper::toUserForSelectorDTO)
                .collect(Collectors.toList());
    }

    protected List<User> getUserForProjectByPublicId(UUID publicProjectId) {
        return projectRepository.findUsersByProjectPublicId(publicProjectId);
    }
}
