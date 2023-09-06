package com.erp.hr.holiday.controller;

import com.erp.hr.common.model.Response;
import com.erp.hr.holiday.dto.HolidayRequestDto;
import com.erp.hr.holiday.dto.HolidayStatusPatchDto;
import com.erp.hr.holiday.model.Holiday;
import com.erp.hr.holiday.model.HolidayStatus;
import com.erp.hr.holiday.service.HolidayService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping(path = "/api/holidays")
@CrossOrigin(origins = "*")
public class HolidayController {

    private final HolidayService holidayService;

    @GetMapping("")
    public List<Holiday> getMyHolidays() {
        return this.holidayService.getHolidaysByEmployee();
    }

    @GetMapping("/all")
    public List<Holiday> getHolidaysAll() {
        return this.holidayService.getHolidaysAll();
    }

    @PostMapping("/add")
    public ResponseEntity<Response> addHoliday(@Valid @RequestBody HolidayRequestDto holidayRequestDto) {
        this.holidayService.addHoliday(holidayRequestDto);
        Response response = new Response();
        response.setStatusCode("201");
        response.setStatusMsg("Holiday added successfully");
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    @PatchMapping("/status")
    public ResponseEntity<Response> patchHolidayStatus(@Valid @RequestBody HolidayStatusPatchDto holidayStatusPatchDto) {
        this.holidayService.modifyStatus(holidayStatusPatchDto.getHolidayId(), holidayStatusPatchDto.getStatus());
        Response response = new Response();
        response.setStatusCode("204");
        response.setStatusMsg("modified successfully");
        return ResponseEntity.status(HttpStatus.NO_CONTENT).body(response);
    }
}
