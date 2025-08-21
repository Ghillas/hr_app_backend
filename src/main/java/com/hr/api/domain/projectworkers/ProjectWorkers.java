package com.hr.api.domain.projectworkers;

import com.hr.api.domain.employee.Employee;
import com.hr.api.domain.project.Project;

import lombok.Data;

//public record ProjectWorkers(long id, Employee employee, Project project, int grade) {
//}

@Data
public class ProjectWorkers {

    private long id;

    private Employee employee;

    private Project project;

    private int grade;
}