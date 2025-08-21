package com.hr.api.application.restapiadapter.employee;

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

import com.hr.api.application.restapiadapter.project.ProjectDTO;
import com.hr.api.application.restapiadapter.project.ProjectDTOMapper;
import com.hr.api.domain.employee.Employee;
import com.hr.api.domain.employee.EmployeeService;
import com.hr.api.domain.projectworkers.ProjectWorkersService;

@RestController
public class EmployeeController {

    @Autowired
    private EmployeeService employeeService;

    @Autowired
    private ProjectWorkersService projectWorkersService;

    @GetMapping("/employees")
    public List<EmployeeDTO> getEmployees() {
        return employeeService
            .getEmployees()
            .stream()
            .map(EmployeeDTOMapper::fromDomainToDTO)
            .toList();
    }

    @GetMapping("/employee/{id}")
    public EmployeeDTO getEmployee(@PathVariable("id") final Long id) {
        Optional<Employee> employee = employeeService.getEmployee(id);
        if(employee.isPresent()) {
            return EmployeeDTOMapper.fromDomainToDTO(employee.get());
        } else {
            return null;
        }
    }

    @PostMapping("/employee")
    public EmployeeDTO createEmployee(@RequestBody EmployeeDTO employee) {
        return EmployeeDTOMapper.fromDomainToDTO(
            employeeService.saveEmployee(EmployeeDTOMapper.fromDTOToDomain(employee))
        );
    }

    @PutMapping("/employee/{id}")
    public EmployeeDTO updateEmployee(@PathVariable("id") final Long id, @RequestBody EmployeeDTO employee) {
        Optional<EmployeeDTO> e = employeeService.getEmployee(id).map(EmployeeDTOMapper::fromDomainToDTO);
        if (e.isPresent()) {
            EmployeeDTO updatedEmployee = new EmployeeDTO();
            updatedEmployee.setId(e.get().getId());
            updatedEmployee.setFirstName(employee.getFirstName() == null ? e.get().getFirstName() : employee.getFirstName());
            updatedEmployee.setLastName(employee.getLastName() == null ? e.get().getLastName() : employee.getLastName());
            updatedEmployee.setMail(employee.getMail() == null ? e.get().getMail() : employee.getMail());
            updatedEmployee.setPassword(employee.getPassword() == null ? e.get().getPassword() : employee.getPassword());
            
            employeeService.saveEmployee(EmployeeDTOMapper.fromDTOToDomain(updatedEmployee));
            return updatedEmployee;
        } else {
            return null;
        }
    }

    @DeleteMapping("/employee/{id}")
    public void deleteEmployee(@PathVariable("id") final Long id) {
        employeeService.deleteEmployee(id);
    }

    //TODO : I dont know if this is the right place for these function, or if I should move these to another file

    @GetMapping("/employee/{id}/project")
    public List<ProjectDTO> getProjectFromEmployee(@PathVariable("id") final long id) {
        return projectWorkersService
            .getProjectsFromEmployeeById(id)
            .stream()
            .map(ProjectDTOMapper::fromDomainToDTO)
            .toList();
    }

    @PostMapping("/employee/{employee_id}/project/{project_id}")
    public void addEmployeeToProject(@PathVariable("employee_id") final long employeeId, @PathVariable("project_id") final long projectId) {
        projectWorkersService.addEmployeeToProject(employeeId, projectId);
    }

    @DeleteMapping("/employee/{employee_id}/project/{project_id}")
    public void removeEmployeeFromProject(@PathVariable("employee_id") final long employeeId, @PathVariable("project_id") final long projectId) {
        projectWorkersService.removeEmployeeFromProject(employeeId, projectId);
    }

    @GetMapping("project/{id}/employees")
    public List<EmployeeDTO> getEmployeesFromProject(@PathVariable("id") final long id) {
        return projectWorkersService
            .getEmployeesFromProjectById(id)
            .stream()
            .map(EmployeeDTOMapper::fromDomainToDTO)
            .toList();
    }
    
}
