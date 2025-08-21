package com.hr.api.domain.employee;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import lombok.Data;

@Data
@Service
public class EmployeeService {

    @Autowired
    private EmployeeFetcher employeeFetcher;

    public Optional<Employee> getEmployee(final Long id) {
        return employeeFetcher.findById(id);
    }

    public List<Employee> getEmployees() {
        return employeeFetcher.findAll();
    }

    public void deleteEmployee(final Long id) {
        employeeFetcher.deleteById(id);
    }

    public Employee saveEmployee(Employee employee) {
        Employee savedEmployee = employeeFetcher.save(employee);
        return savedEmployee;
    }
}
