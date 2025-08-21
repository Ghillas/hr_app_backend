package com.hr.api.domain.projectworkers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hr.api.domain.employee.Employee;
import com.hr.api.domain.employee.EmployeeFetcher;
import com.hr.api.domain.project.Project;
import com.hr.api.domain.project.ProjectFetcher;

@Service
public class ProjectWorkersService {

    @Autowired
    private ProjectWorkersFetcher projectWorkersFetcher;

    @Autowired
    private ProjectFetcher projectFetcher;

    @Autowired
    private EmployeeFetcher employeeFetcher;

    public List<Project> getProjectsFromEmployeeById(Long employeeId) {
        return projectWorkersFetcher
        .findByEmployeeId(
            employeeId
        ).stream()
        .map(projectWorker -> projectWorker.getProject())
        .toList();
    }

    public List<Employee> getEmployeesFromProjectById(Long projectId) {
        return projectWorkersFetcher
        .findByProjectId(
            projectId
        ).stream()
        .map(ProjectWorkers::getEmployee)
        .toList();
    }

    public void addEmployeeToProject(Long employeeId, Long projectId) {
        Employee employee = employeeFetcher.findById(employeeId).orElseThrow();
        Project project = projectFetcher.findById(projectId).orElseThrow();
        ProjectWorkers addEmployee = new ProjectWorkers();
        addEmployee.setEmployee(employee);
        addEmployee.setProject(project);
        projectWorkersFetcher.saveProjectWorkers(addEmployee);
    }

    public void removeEmployeeFromProject(Long employeeId, Long projectId) {
        projectWorkersFetcher.deleteByEmployeeIdAndProjectId(employeeId, projectId);
    }

}
