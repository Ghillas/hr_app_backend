package com.hr.api.domain.employee;

import lombok.Data;


@Data
public class Employee {

    private long Id;

    private String firstName;

    private String lastName;

    private String mail;

    private String password;
}