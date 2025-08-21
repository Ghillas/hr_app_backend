package com.hr.api.domain.project;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ProjectService {

    @Autowired
    private ProjectFetcher projectFetcher;

    public Optional<Project> findById(long id) {
        return projectFetcher.findById(id);
    }

    public List<Project> findAll() {
        return projectFetcher.findAll();
    }

    public void deleteById(long id) {
        projectFetcher.deleteById(id);
    }

    public Project saveProject(Project project) {
        return projectFetcher.save(project);
    }
}
