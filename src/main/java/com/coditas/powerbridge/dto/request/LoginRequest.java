package com.coditas.powerbridge.dto.request;

import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter @Setter
@NoArgsConstructor
public class LoginRequest {
    @NotBlank
    private String identity;
    @NotBlank
    private String password;
}
