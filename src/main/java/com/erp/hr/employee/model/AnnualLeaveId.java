package com.erp.hr.employee.model;

import lombok.AllArgsConstructor;

import java.io.Serializable;

@AllArgsConstructor
public class AnnualLeaveId implements Serializable {

    private int employeeId;
    private int year;
}
