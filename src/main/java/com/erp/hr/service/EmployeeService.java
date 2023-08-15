package com.erp.hr.service;

import com.erp.hr.model.Employee;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class EmployeeService {
    private PasswordEncoder passwordEncoder;

    @Autowired
    public EmployeeService(PasswordEncoder passwordEncoder) {
        this.passwordEncoder = passwordEncoder;
    }

    public void registerEmployee(Employee employee) {
        String encodedPassword = this.passwordEncoder.encode(employee.getPassword());
        employee.setPassword(encodedPassword);
    }
}
