package com.hr.api.domain.project;

import java.util.List;
import java.util.Optional;

public interface ProjectFetcher {

    public Optional<Project> findById(long id);    

    public List<Project> findAll();

    public void deleteById(long id);

    public Project save(Project project);
}
