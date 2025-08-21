package com.hr.api.infrastructure.postgresadapter.employee;

import java.util.List;
import java.util.Optional;
import java.util.stream.StreamSupport;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.hr.api.domain.employee.Employee;
import com.hr.api.domain.employee.EmployeeFetcher;

@Component
public class EmployeeAdapter implements EmployeeFetcher {

    @Autowired
    private EmployeeRepository employeeRepository;

    /* 
    public EmployeeAdapter(EmployeeRepository employeeRepository) {
        this.employeeRepository = employeeRepository;
    }*/

    @Override
    public Optional<Employee> findById(long id) {
        return employeeRepository.findById(id).map(EmployeeMapper::fromEntityToDomain);
    }

    @Override
    public List<Employee> findAll() {
        return StreamSupport
            .stream(employeeRepository.findAll().spliterator(), false)
            .map(EmployeeMapper::fromEntityToDomain)
            .toList();
    }

    @Override
    public void deleteById(long id) {
        employeeRepository.deleteById(id);
    }

    @Override
    public Employee save(Employee employee) {
        return EmployeeMapper
            .fromEntityToDomain(
                employeeRepository
                .save(
                    EmployeeMapper
                    .fromDomainToEntity(employee)
                )
            );
    }

}
