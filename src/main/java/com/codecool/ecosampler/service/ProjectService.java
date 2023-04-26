package com.codecool.ecosampler.service;

import com.codecool.ecosampler.controller.dto.project.NewProject;
import com.codecool.ecosampler.domain.Project;
import com.codecool.ecosampler.domain.User;
import com.codecool.ecosampler.exeption.NotFoundException;
import com.codecool.ecosampler.repository.ProjectRepository;
import com.codecool.ecosampler.repository.UserRepository;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

public class ProjectService {
    private UserRepository userRepository;
    private ProjectRepository projectRepository;

    @ResponseStatus(HttpStatus.CREATED)
    public Long createProject(NewProject newProject) {
        return projectRepository.save(new Project(newProject.name(), newProject.desc())).getId();
    }

    @ResponseStatus(HttpStatus.OK)
    public void addUserToProject(Long projectID, Long userID) {
        User user = userRepository.findById(userID)
                .orElseThrow(() ->
                        new NotFoundException("This user doesn't exist with this id: " + userID));
        Project project = projectRepository.findById(projectID).orElseThrow();
    }


}
