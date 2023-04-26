package com.codecool.ecosampler.controller;

import com.codecool.ecosampler.controller.dto.project.NewProject;
import com.codecool.ecosampler.controller.dto.project.ProjectAndUserId;
import com.codecool.ecosampler.service.ProjectService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

@AllArgsConstructor
@RestController
@RequestMapping("/api/v1/project")
public class ProjectController {
    private ProjectService projectService;
    @PostMapping("/")
    public Long createProject(@RequestBody NewProject newProject){
return projectService.createProject(newProject);
    }
    @PutMapping("/addUser/")
    public void addUserToProject(@RequestBody ProjectAndUserId request){
        projectService.addUserToProject(request.projectID(),request.userID());
    }
}
