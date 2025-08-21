package com.hr.api.infrastructure.postgresadapter.project;

import java.util.List;
import java.util.Optional;
import java.util.stream.StreamSupport;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.hr.api.domain.project.Project;
import com.hr.api.domain.project.ProjectFetcher;

@Component
public class ProjectAdapter implements ProjectFetcher{

    @Autowired
    private ProjectRepository projectRepository;

    @Override
    public Optional<Project> findById(long id) {
        return projectRepository
            .findById(id)
            .map(ProjectEntityMapper::fromEntityToDomain);
    }

    @Override
    public List<Project> findAll() {
        return StreamSupport
            .stream(projectRepository.findAll().spliterator(), false)
            .map(ProjectEntityMapper::fromEntityToDomain)
            .toList();
    }

    @Override
    public void deleteById(long id) {
        projectRepository.deleteById(id);
    }

    @Override
    public Project save(Project project) {
        ProjectEntity projectToSave = ProjectEntityMapper.fromDomainToEntity(project);
        Project projectSaved = ProjectEntityMapper.fromEntityToDomain(
            projectRepository.save(projectToSave)
        );
        return projectSaved;
    }

}
