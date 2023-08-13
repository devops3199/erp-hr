package com.erp.hr.controller;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping(path = "/api/employee")
@CrossOrigin(origins = "*")
public class EmployeeController {


    @GetMapping("/all")
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
}
