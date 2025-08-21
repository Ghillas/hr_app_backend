package com.hr.api.domain.employee;

import java.util.List;
import java.util.Optional;

public interface EmployeeFetcher {

    public Optional<Employee> findById(long id);

    public List<Employee> findAll();

    public void deleteById(long id);

    public Employee save(Employee employee);
}
