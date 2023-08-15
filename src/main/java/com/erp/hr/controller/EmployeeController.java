package com.erp.hr.controller;

import com.erp.hr.dto.employee.EmployeeRequestDto;
import com.erp.hr.model.Employee;
import com.erp.hr.model.Response;
import com.erp.hr.repository.EmployeeRepository;
import com.erp.hr.service.EmployeeService;
import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@Slf4j
@RestController
@RequestMapping(path = "/api/employees")
@CrossOrigin(origins = "*")
public class EmployeeController {

    private final EmployeeService employeeService;

    @Autowired
    public EmployeeController(EmployeeService employeeService) {
        this.employeeService = employeeService;
    }

    @GetMapping("")
    public List<String> getEmployees() {
        List<String> employees = new ArrayList<>();
        employees.add("Ray");
        employees.add("John");
        employees.add("Sam");
        employees.add("Alex");
        employees.add("Charlie");
        employees.add("Jack");
        return employees;
    }

    @GetMapping("/{id}")
    public String getEmployee(@PathVariable int id) {
        return "ID = " + id;
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
