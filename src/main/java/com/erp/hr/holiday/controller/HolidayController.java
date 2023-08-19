package com.erp.hr.holiday.controller;

import com.erp.hr.common.model.Response;
import com.erp.hr.holiday.dto.HolidayRequestDto;
import com.erp.hr.holiday.service.HolidayService;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RestController
@AllArgsConstructor
@RequestMapping(path = "/api/holidays")
@CrossOrigin(origins = "*")
public class HolidayController {

    private final HolidayService holidayService;

    @PostMapping("/add")
    public ResponseEntity<Response> addHoliday(@Valid @RequestBody HolidayRequestDto holidayRequestDto) {
        this.holidayService.addHoliday(holidayRequestDto);
        Response response = new Response();
        response.setStatusCode("201");
        response.setStatusMsg("Holiday added successfully");
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }
}
