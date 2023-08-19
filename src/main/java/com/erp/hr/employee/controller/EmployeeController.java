package com.erp.hr.employee.controller;

import com.erp.hr.employee.dto.EmployeeRequestDto;
import com.erp.hr.employee.dto.EmployeeResponseDto;
import com.erp.hr.common.model.Response;
import com.erp.hr.employee.service.EmployeeService;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RestController
@AllArgsConstructor
@RequestMapping(path = "/api/employees")
@CrossOrigin(origins = "*")
public class EmployeeController {

    private final EmployeeService employeeService;

    @GetMapping("")
    public List<EmployeeResponseDto> getEmployees() {
        return this.employeeService.getEmployees();
    }

    @GetMapping("/{id}")
    public EmployeeResponseDto getEmployee(@PathVariable int id) {
        return this.employeeService.getEmployee(id);
    }

    @PostMapping("/register")
    public ResponseEntity<Response> registerEmployee(@Valid @RequestBody EmployeeRequestDto employeeRequestDto) {
        this.employeeService.registerEmployee(employeeRequestDto);
        Response response = new Response();
        response.setStatusCode("201");
        response.setStatusMsg("Employee registered successfully");
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }
}
