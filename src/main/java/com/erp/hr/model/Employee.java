package com.erp.hr.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.Date;

@Getter
@Setter
@Entity
public class Employee extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;

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

    @NotBlank(message="joinDate is required")
    private Date joinDate;

    private LocalDateTime deletedAt;
}
