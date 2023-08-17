package com.erp.hr.employee.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Builder;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.Date;

@Builder
@Data
public class EmployeeResponseDto {

    private int employeeId;

    private String email;

    private String firstName;

    private String lastName;

    private String phoneNumber;

    private LocalDateTime lastLogin;

    @JsonFormat(pattern = "yyyy-MM-dd", timezone = "Asia/Seoul")
    private Date joinDate;

    private String roleName;

    private String divisionName;
}
