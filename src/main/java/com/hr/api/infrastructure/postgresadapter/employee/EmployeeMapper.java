package com.hr.api.infrastructure.postgresadapter.employee;

import com.hr.api.domain.employee.Employee;

public interface EmployeeMapper {

    public static Employee fromEntityToDomain(EmployeeEntity employeeEntity) {
        Employee e = new Employee();
        e.setId(employeeEntity.getId());
        e.setFirstName(employeeEntity.getFirstName());
        e.setLastName(employeeEntity.getLastName());
        e.setMail(employeeEntity.getMail());
        e.setPassword(employeeEntity.getPassword());
        return e;
    }

    public static EmployeeEntity fromDomainToEntity(Employee employee) {
        return new EmployeeEntity(
            employee.getId(),
            employee.getFirstName(),
            employee.getLastName(),
            employee.getMail(),
            employee.getPassword()
        );
    }
}
