package com.erp.hr.holiday.model;

import com.erp.hr.common.model.BaseEntity;
import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.GenericGenerator;

import java.time.LocalDate;

@Builder
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Holiday extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO, generator = "native")
    @GenericGenerator(name = "native")
    private int holidayId;

    private int employeeId;

    @Enumerated(EnumType.STRING)
    private HolidayStatus status;

    @Enumerated(EnumType.STRING)
    private HolidayType type;

    @JsonFormat(pattern = "yyyy-MM-dd", timezone = "Asia/Seoul")
    private LocalDate start;

    @JsonFormat(pattern = "yyyy-MM-dd", timezone = "Asia/Seoul")
    private LocalDate end;

    private int count;

    private String note;

    private Integer approvedBy;

    private Integer rejectedBy;
}
