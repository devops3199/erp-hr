package com.erp.hr.auth.controller;

import com.erp.hr.auth.dto.AuthRequestDto;
import com.erp.hr.auth.service.AuthService;
import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RestController
@RequestMapping(path = "/api/auth")
@CrossOrigin(origins = "*")
public class AuthController {

    private final AuthService authService;

    @Autowired
    public AuthController(AuthService authService) {
        this.authService = authService;
    }

    @PostMapping("/login")
    public ResponseEntity<?> login(@Valid @RequestBody AuthRequestDto authRequestDto) {
        String token = this.authService.generateToken(authRequestDto.getEmail(), authRequestDto.getPassword());
        return ResponseEntity.status(HttpStatus.CREATED).body(token);
    }
}
