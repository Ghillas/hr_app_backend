package com.hr.api.infrastructure.postgresadapter.project;

import com.hr.api.domain.project.Project;

public interface ProjectEntityMapper {

    static Project fromEntityToDomain(ProjectEntity project) {
        Project p = new Project();
        p.setId(project.getId());
        p.setName(project.getProjectName());
        p.setDescription(project.getDescription());
        return p;
    }

    static ProjectEntity fromDomainToEntity(Project project) {
        ProjectEntity projectEntity = new ProjectEntity();
        projectEntity.setId(project.getId());
        projectEntity.setProjectName(project.getName());
        projectEntity.setDescription(project.getDescription());
        return projectEntity;
    }
}



// !!!!!!!!!! Les employees du projet ne sont pas transferer entre les différentes couches, je ne sais pas encore si cela peut créer un bug