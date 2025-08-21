package com.hr.api.application.restapiadapter.employee;

import lombok.Data;

//public record EmployeeDTO(long id, String firstName, String lastName, String mail, String password) {
//}


@Data
public class EmployeeDTO {

    private long Id;

    private String firstName;

    private String lastName;

    private String mail;

    private String password;

    //private List<Project> projects = new ArrayList<>();
}