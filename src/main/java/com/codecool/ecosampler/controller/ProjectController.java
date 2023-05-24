package com.codecool.ecosampler.controller;

import com.codecool.ecosampler.controller.dto.form.NewForm;
import com.codecool.ecosampler.controller.dto.project.ModifyUsersOnProject;
import com.codecool.ecosampler.controller.dto.project.NewProject;
import com.codecool.ecosampler.controller.dto.project.ProjectDTO;
import com.codecool.ecosampler.controller.dto.user.UserForSelectDTO;
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
    public List<ProjectDTO> getAllProjectDTO() {
        return projectService.getAllProjectDTO();
    }

    @PostMapping
    @ResponseStatus(value = HttpStatus.CREATED)
    public ProjectDTO addNewProject(@RequestBody NewProject project) {
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

    @PutMapping("/modify-users/{projectID}")
    public void modifyUsersOnProject(@RequestBody ModifyUsersOnProject modifyUsersOnProject, @PathVariable UUID projectID) {
        projectService.modifyUsersOnProject(modifyUsersOnProject, projectID);
    }

    @PostMapping("/addForm/{projectID}")
    @ResponseStatus(HttpStatus.CREATED)
    public void addFormToProject(@RequestBody NewForm newForm, @PathVariable UUID projectID) {
        projectService.addFormToProject(newForm, projectID);
    }

    @PutMapping("{publicProjectId}")
    public ProjectDTO updateProject(@PathVariable UUID publicProjectId, @RequestBody ProjectDTO project) {
        return projectService.updateProject(publicProjectId, project);
    }
    @GetMapping("users/{publicProjectId}")
    public List<UserForSelectDTO> getUsersForProjectByPublicId(@PathVariable UUID publicProjectId) {
        return projectService.getUserForSelectDTOForProjectByPublicId(publicProjectId);
    }
}
