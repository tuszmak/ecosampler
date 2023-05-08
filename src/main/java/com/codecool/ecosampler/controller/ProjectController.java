package com.codecool.ecosampler.controller;

import com.codecool.ecosampler.controller.dto.ProjectDTO;
import com.codecool.ecosampler.controller.dto.project.NewProject;
import com.codecool.ecosampler.controller.dto.project.ProjectAndUserId;
import com.codecool.ecosampler.domain.Project;
import com.codecool.ecosampler.service.ProjectService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@AllArgsConstructor
@RestController
@RequestMapping("/api/v1/project")
public class ProjectController {
    private ProjectService projectService;

    @GetMapping
    public List<ProjectDTO> getAllProject() {
        return projectService.getAllProjectDTO();
    }

    @PostMapping
    @ResponseStatus(value = HttpStatus.CREATED)
    public UUID addNewProject(@RequestBody NewProject project) {
        return projectService.addNewProject(project);
    }

    @GetMapping("/by-user/{userPublicId}")
    public List<ProjectDTO> getProjectsByUserId(@PathVariable UUID userPublicId) {
        return projectService.getProjectsDTOByUserPublicId(userPublicId);
    }

    @DeleteMapping("{publicId}")
    public void deleteProject(@PathVariable UUID publicId) {
        projectService.deleteProject(publicId);
    }

    @ResponseStatus(HttpStatus.OK)
    @PutMapping("/addUser")
    public void addUserToProject(@RequestBody ProjectAndUserId projectAndUserId) {
        projectService.addUserToProject(projectAndUserId);
    }

    @PutMapping("{publicProjectId}")
    public Project updateProject(@PathVariable UUID publicProjectId, @RequestBody ProjectDTO project) {
        return projectService.updateProject(publicProjectId, project);
    }
}
