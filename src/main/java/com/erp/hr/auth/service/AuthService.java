package com.erp.hr.auth.service;

import com.erp.hr.employee.repository.EmployeeRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;

@Slf4j
@RequiredArgsConstructor
@Service
public class AuthService {

    private final EmployeeRepository employeeRepository;
    private final AuthenticationManager authenticationManager;
    private final JwtService jwtService;

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

    public void setLastLogin(String email) {
        this.employeeRepository.updateLastLoginByEmail(LocalDateTime.now(), email);
    }
}
