package com.hr.api.infrastructure.postgresadapter.projectworkers;

import com.hr.api.infrastructure.postgresadapter.employee.EmployeeEntity;
import com.hr.api.infrastructure.postgresadapter.project.ProjectEntity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.Data;

@Data
@Entity
@Table(name = "project_workers")
public class ProjectWorkersEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @ManyToOne
    private EmployeeEntity employee;

    @ManyToOne
    private ProjectEntity project;

    private int grade;

}
