package com.erp.hr.holiday.dto;

import com.erp.hr.holiday.model.HolidayType;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.time.LocalDate;

@Data
public class HolidayRequestDto {

    @NotNull
    @Enumerated(EnumType.STRING)
    private HolidayType type;

    @NotNull
    private LocalDate start;

    @NotNull
    private LocalDate end;

    private String note;
}
