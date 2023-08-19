package com.erp.hr.employee.model;

import com.erp.hr.common.model.BaseEntity;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@IdClass(AnnualLeaveId.class)
@Getter
@Setter
@NoArgsConstructor
@Entity
public class AnnualLeave extends BaseEntity {

    @Id
    private int employeeId;

    @Id
    private int year;

    private int total;
}
