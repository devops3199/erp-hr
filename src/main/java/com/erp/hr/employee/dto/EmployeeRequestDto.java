package com.erp.hr.employee.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;
import lombok.*;

import java.util.Date;

@Data
public class EmployeeRequestDto {

    @Email
    @NotNull
    private String email;

    @NotNull
    private String password;

    @NotNull
    private String firstName;

    @NotNull
    private String lastName;

    @NotNull
    private String phoneNumber;

    @NotNull
    private int divisionId;

    @NotNull
    private int roleId;

    @NotNull
    private Date joinDate;
}
