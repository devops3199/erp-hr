package com.erp.hr.controller;

import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping(path = "/api/employees")
@CrossOrigin(origins = "*")
public class EmployeeController {


    @GetMapping()
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
}
