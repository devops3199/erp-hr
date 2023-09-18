package com.erp.hr.holiday.service;

import com.erp.hr.employee.model.Employee;
import com.erp.hr.holiday.dto.HolidayRequestDto;
import com.erp.hr.holiday.model.Holiday;
import com.erp.hr.holiday.model.HolidayStatus;
import com.erp.hr.holiday.repository.HolidayRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;

import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.List;

@Slf4j
@RequiredArgsConstructor
@Service
public class HolidayService {

    private final int TODAY = 1;
    private final HolidayRepository holidayRepository;

    public List<Holiday> getHolidaysByEmployee() {
        var employee = (Employee) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        return this.holidayRepository.findByEmployeeId(employee.getEmployeeId());
    }

    public List<Holiday> getHolidaysAll() {
        if (!hasAdminLeaderPermission()) {
            throw new ResponseStatusException(HttpStatus.UNAUTHORIZED);
        }

        return this.holidayRepository.findAll();
    }

    @Transactional
    public void addHoliday(HolidayRequestDto holidayRequestDto) {
        var employee = (Employee) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
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

    public void modifyStatus(int holidayId, HolidayStatus status) {
        if (!hasAdminLeaderPermission()) {
            throw new ResponseStatusException(HttpStatus.UNAUTHORIZED);
        }

        var employee = (Employee) SecurityContextHolder.getContext().getAuthentication().getPrincipal();

        this.holidayRepository.updateStatusByHolidayId(holidayId, status, employee.getEmployeeId(), LocalDateTime.now());
    }

    private boolean hasAdminLeaderPermission() {
        var authList = SecurityContextHolder.getContext().getAuthentication().getAuthorities();
        return authList.stream().allMatch(role -> role.getAuthority().equals("admin") || role.getAuthority().equals("leader"));
    }
}
