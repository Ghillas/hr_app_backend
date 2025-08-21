package com.hr.api.application.restapiadapter.employee;

import com.hr.api.domain.employee.Employee;

public interface EmployeeDTOMapper {

    static EmployeeDTO fromDomainToDTO(Employee employee) {
        EmployeeDTO employeeDTO = new EmployeeDTO();
        employeeDTO.setId(employee.getId());
        employeeDTO.setFirstName(employee.getFirstName());
        employeeDTO.setLastName(employee.getLastName());
        employeeDTO.setMail(employee.getMail());
        employeeDTO.setPassword(employee.getPassword());
        return employeeDTO;
    }

    static Employee fromDTOToDomain(EmployeeDTO employeeDTO) {
        Employee employee = new Employee();
        employee.setId(employeeDTO.getId());
        employee.setFirstName(employeeDTO.getFirstName());
        employee.setLastName(employeeDTO.getLastName());
        employee.setMail(employeeDTO.getMail());
        employee.setPassword(employeeDTO.getPassword());
        return employee;
    }
}
