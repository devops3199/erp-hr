package com.erp.hr.holiday.service;

import com.erp.hr.employee.repository.EmployeeRepository;
import com.erp.hr.holiday.dto.HolidayRequestDto;
import com.erp.hr.holiday.model.Holiday;
import com.erp.hr.holiday.model.HolidayStatus;
import com.erp.hr.holiday.repository.HolidayRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.temporal.ChronoUnit;
import java.util.List;

@Slf4j
@RequiredArgsConstructor
@Service
public class HolidayService {

    private final int TODAY = 1;
    private final EmployeeRepository employeeRepository;
    private final HolidayRepository holidayRepository;

    @Transactional
    public List<Holiday> getHolidaysByEmployee() {
        String email = SecurityContextHolder.getContext().getAuthentication().getName();
        var employee = this.employeeRepository.findByEmail(email).orElseThrow(() -> new RuntimeException("No such employee found"));

        return this.holidayRepository.findByEmployeeId(employee.getEmployeeId());
    }

    public List<Holiday> getHolidaysAll() {
        return this.holidayRepository.findAll();
    }

    @Transactional
    public void addHoliday(HolidayRequestDto holidayRequestDto) {
        String email = SecurityContextHolder.getContext().getAuthentication().getName();
        var employee = this.employeeRepository.findByEmail(email).orElseThrow(() -> new RuntimeException("No such employee found"));
        long days = ChronoUnit.DAYS.between(holidayRequestDto.getStart(), holidayRequestDto.getEnd());

        var holiday = Holiday.builder()
                .employeeId(employee.getEmployeeId())
                .status(HolidayStatus.PENDING)
                .type(holidayRequestDto.getType())
                .start(holidayRequestDto.getStart())
                .end(holidayRequestDto.getEnd())
                .count((int) days + TODAY)
                .note(holidayRequestDto.getNote())
                .build();

        this.holidayRepository.save(holiday);
    }
}
