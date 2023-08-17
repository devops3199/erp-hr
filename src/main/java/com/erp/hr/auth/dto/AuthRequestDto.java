package com.erp.hr.auth.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class AuthRequestDto {

    @Email
    @NotNull
    private String email;

    @NotNull
    private String password;
}
