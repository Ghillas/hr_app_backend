package com.hr.api.application.restapiadapter.project;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.hr.api.domain.project.ProjectService;

@RestController
public class ProjectController {

    @Autowired
    private ProjectService projectService;

    @GetMapping("/projects")
    public List<ProjectDTO> getProjects() {
        return projectService
            .findAll()
            .stream()
            .map(ProjectDTOMapper::fromDomainToDTO)
            .toList();
    }

    @GetMapping("/project/{id}")
    public ProjectDTO getProject(@PathVariable("id") final Long id) {
        Optional<ProjectDTO> project = projectService.findById(id).map(ProjectDTOMapper::fromDomainToDTO);
        if (project.isPresent()) {
            return project.get();
        } else {
            return null;
        }
    }

    @PostMapping("project")
    public ProjectDTO createProject(@RequestBody ProjectDTO projectDTO) {
        return ProjectDTOMapper
            .fromDomainToDTO(
                projectService
                .saveProject(ProjectDTOMapper.fromDTOToDomain(projectDTO)
                )
            );
    }

    @PutMapping("project/{id}")
    public ProjectDTO updateProject(@PathVariable("id") final Long id, @RequestBody ProjectDTO projectDTO) {
        Optional<ProjectDTO> project = projectService.findById(id).map(ProjectDTOMapper::fromDomainToDTO);
        if (project.isPresent()) {
            ProjectDTO updatedProject = new ProjectDTO();
            updatedProject.setId(id);
            updatedProject.setName(projectDTO.getName() == null ? project.get().getName() : projectDTO.getName());
            updatedProject.setDescription(projectDTO.getDescription() == null ? project.get().getDescription() : projectDTO.getDescription());
        
            projectService.saveProject(ProjectDTOMapper.fromDTOToDomain(updatedProject));
            return updatedProject;
        } else {
            return null;
        }
    }

    @DeleteMapping("project/{id}")
    public void deleteProject(@PathVariable("id") final Long id) {
        projectService.deleteById(id);
    }


}
