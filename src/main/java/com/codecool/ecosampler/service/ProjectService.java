package com.codecool.ecosampler.service;

import com.codecool.ecosampler.domain.Project;
import com.codecool.ecosampler.exeption.BadRequestException;
import com.codecool.ecosampler.exeption.NotFoundException;
import com.codecool.ecosampler.repository.ProjectRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@AllArgsConstructor
@Service
public class ProjectService {
    private ProjectRepository projectRepository;

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
        if (requestProject.getName() != null)
            project.setName(requestProject.getName());
        if (requestProject.getDescription() != null)
            project.setDescription(requestProject.getDescription());
        // TODO The rest
        return project;
    }
}
