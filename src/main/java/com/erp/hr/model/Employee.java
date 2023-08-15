package com.erp.hr.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.Date;

@Data
@Entity
public class Employee extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int employeeId;

    @Email
    @NotBlank(message="email is required")
    private String email;

    @NotBlank(message="password is required")
    private String password;

    @NotBlank(message="fistName is required")
    private String firstName;

    @NotBlank(message="lastName is required")
    private String lastName;

    @NotBlank(message="phoneNumber is required")
    private String phoneNumber;

    private LocalDateTime lastLogin;

    private Date joinDate;

    private LocalDateTime deletedAt;
}
