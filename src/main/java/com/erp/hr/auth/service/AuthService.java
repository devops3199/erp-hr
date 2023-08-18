package com.erp.hr.auth.service;

import com.erp.hr.employee.repository.EmployeeRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

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

    public Map<String, String> getToken(String email, String password) {
        authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(email, password));

        var user = this.employeeRepository.findByEmail(email)
                .orElseThrow(() -> new IllegalArgumentException("Invalid email or password."));

        String token = this.jwtService.generateToken(user);
        long expiredAt = this.jwtService.getExpirationInTimestamp(token);

        Map<String, String> result = new HashMap<>();

        result.put("token", token);
        result.put("expiredAt", String.valueOf(expiredAt));

        return result;
    }
}
