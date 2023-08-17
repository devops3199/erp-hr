package com.erp.hr.auth.service;

import com.erp.hr.employee.repository.EmployeeRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class AuthService {

    private final EmployeeRepository employeeRepository;

    @Autowired
    public AuthService(EmployeeRepository employeeRepository) {
        this.employeeRepository = employeeRepository;
    }

    public String generateToken(String email, String password) {
        return "token";
    }
}
