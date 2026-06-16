package com.coditas.powerbridge.dto.request;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class EmployeeRequest {
    @NotNull
    private String name;
    @Email
    private String email;
    @NotNull
    private String password;
}
