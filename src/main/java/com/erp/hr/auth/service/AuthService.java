package com.erp.hr.auth.service;

import com.erp.hr.employee.repository.EmployeeRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class AuthService {

    private final EmployeeRepository employeeRepository;
    private final AuthenticationManager authenticationManager;
    private final JwtService jwtService;

    @Autowired
    public AuthService(EmployeeRepository employeeRepository, AuthenticationManager authenticationManager, JwtService jwtService) {
        this.employeeRepository = employeeRepository;
        this.authenticationManager = authenticationManager;
        this.jwtService = jwtService;
    }


    public String generateToken(String email, String password) {
        authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(email, password));

        var user = this.employeeRepository.findByEmail(email)
                .orElseThrow(() -> new IllegalArgumentException("Invalid email or password."));

        var accessToken = this.jwtService.generateToken(user);

        return accessToken;
    }
}
