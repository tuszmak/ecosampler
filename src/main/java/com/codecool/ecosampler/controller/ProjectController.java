package com.codecool.ecosampler.controller;

import com.codecool.ecosampler.controller.dto.project.ProjectAndUserId;
import com.codecool.ecosampler.domain.Project;
import com.codecool.ecosampler.service.ProjectService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@AllArgsConstructor
@RestController
@RequestMapping("/api/v1/project")
public class ProjectController {
    private ProjectService projectService;

    @GetMapping("/")
    public List<Project> getAllProject() {
        return projectService.getAllProject();
    }

    @PutMapping("/")
    @ResponseStatus(value = HttpStatus.CREATED)
    public Project addNewProject(@RequestBody Project project) {
        return projectService.addNewProject(project);
    }

    @GetMapping("/by-user/{userId}")
    public List<Project> getProjectsByUserId(@PathVariable Long userId) {
        return projectService.getProjectsByUserId(userId);
    }

    @DeleteMapping("{projectId}")
    public void deleteProject(@PathVariable Long projectId) {
        projectService.deleteProject(projectId);
    }

    @ResponseStatus(HttpStatus.OK)
    @PutMapping("/addUser")
    public void addUserToProject(@RequestBody ProjectAndUserId projectAndUserId) {
        projectService.addUserToProject(projectAndUserId.projectID(), projectAndUserId.userID());
    }

    @PostMapping("{projectId}")
    public Project updateProject(@PathVariable Long projectId, @RequestBody Project project) {
        return projectService.updateProject(projectId, project);
    }

}
