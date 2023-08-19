package com.erp.hr.auth.controller;

import com.erp.hr.auth.dto.AuthRequestDto;
import com.erp.hr.auth.service.AuthService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping(path = "/api/auth")
@CrossOrigin(origins = "*")
public class AuthController {

    private final AuthService authService;

    @PostMapping("/login")
    public ResponseEntity<?> login(@Valid @RequestBody AuthRequestDto authRequestDto) {
        var token = this.authService.getToken(authRequestDto.getEmail(), authRequestDto.getPassword());
        this.authService.setLastLogin(authRequestDto.getEmail());
        return ResponseEntity.status(HttpStatus.CREATED).body(token);
    }
}
