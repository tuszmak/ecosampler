package com.codecool.ecosampler.service;

import com.codecool.ecosampler.controller.dto.project.ProjectAndUserId;
import com.codecool.ecosampler.domain.Project;
import com.codecool.ecosampler.domain.User;
import com.codecool.ecosampler.exeption.BadRequestException;
import com.codecool.ecosampler.exeption.NotFoundException;
import com.codecool.ecosampler.repository.ProjectRepository;
import com.codecool.ecosampler.repository.UserRepository;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseStatus;

import java.util.List;

@AllArgsConstructor
@Service
public class ProjectService {
    private ProjectRepository projectRepository;
    private UserRepository userRepository;

    public List<Project> getAllProject() {
        return projectRepository.findAll();
    }

    public Project addNewProject(Project project) {
        try {
            return projectRepository.save(project);
        } catch (Exception e) {
            throw new BadRequestException("There some problem with current data.");
        }
    }


    public List<Project> getProjectsByUserId(Long userId) {
        return projectRepository.findAllByUserId(userId);
    }

    public void deleteProject(Long projectId) {
        if (projectRepository.existsById(projectId)) {
            projectRepository.deleteById(projectId);
            return;
        }
        throw new NotFoundException("Project not exist with Id: " + projectId);
    }

    public Project updateProject(Long projectId, Project requestProject) {
        Project project = projectRepository.findById(projectId)
                .orElseThrow(() -> new NotFoundException("Project not exist with Id: " + projectId));

        return projectRepository.save(
                updateProjectWithRequest(requestProject, project)
        );

    }

    private Project updateProjectWithRequest(Project requestProject, Project project) {
        if (!requestProject.getName().isEmpty())
            project.setName(requestProject.getName());
        if (!requestProject.getDescription().isEmpty())
            project.setDescription(requestProject.getDescription());
        if (!requestProject.getFormList().isEmpty())
            project.setFormList(requestProject.getFormList());
        return project;
    }

    @ResponseStatus(HttpStatus.OK)
    @PutMapping("/addUser")
    public void addUserToProject(@RequestBody ProjectAndUserId projectAndUserId) {
        User user = userRepository.findById(projectAndUserId.userID())
                .orElseThrow(() ->
                        new NotFoundException("This user doesn't exist with this id: " + projectAndUserId.userID()));
        Project project = projectRepository.findById(projectAndUserId.projectID()).orElseThrow();
        user.addProject(project);

    }
}
