package com.erp.hr.holiday.dto;

import com.erp.hr.holiday.model.HolidayStatus;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class HolidayStatusPatchDto {

    @NotNull
    @Enumerated(EnumType.STRING)
    private HolidayStatus status;

    @NotNull
    private int holidayId;
}
