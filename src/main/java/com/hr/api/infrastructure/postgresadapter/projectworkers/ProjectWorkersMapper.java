package com.hr.api.infrastructure.postgresadapter.projectworkers;

import com.hr.api.domain.projectworkers.ProjectWorkers;
import com.hr.api.infrastructure.postgresadapter.employee.EmployeeMapper;
import com.hr.api.infrastructure.postgresadapter.project.ProjectEntityMapper;

public interface ProjectWorkersMapper {

    static ProjectWorkers fromEntityToDomain(ProjectWorkersEntity projectWorkersEntity) {
        ProjectWorkers pw =  new ProjectWorkers();
        pw.setId(projectWorkersEntity.getId());
        pw.setEmployee(EmployeeMapper.fromEntityToDomain(projectWorkersEntity.getEmployee())); 
        pw.setProject(ProjectEntityMapper.fromEntityToDomain(projectWorkersEntity.getProject()));
        pw.setGrade(projectWorkersEntity.getGrade());
        return pw;
    }

    static ProjectWorkersEntity fromDomainToEntity(ProjectWorkers projectWorkers) {
        ProjectWorkersEntity projectWorkersEntity = new ProjectWorkersEntity();
        projectWorkersEntity.setId(projectWorkers.getId());
        projectWorkersEntity
            .setEmployee(
                EmployeeMapper
                    .fromDomainToEntity(
                        projectWorkers.getEmployee()
                        )
                    );
        projectWorkersEntity
            .setProject(
                ProjectEntityMapper
                    .fromDomainToEntity(projectWorkers.getProject()
                )
            );
        projectWorkersEntity.setGrade(projectWorkers.getGrade());
        return projectWorkersEntity;
    }

}
