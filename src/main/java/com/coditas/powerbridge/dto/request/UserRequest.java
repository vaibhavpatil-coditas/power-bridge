package com.coditas.powerbridge.dto.request;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter @Getter
@NoArgsConstructor
public class UserRequest {
    @NotBlank
    private String name;
    @NotBlank
    private String username;
    @Email
    private String email;
    @NotBlank
    @Size(min = 8)
    private String password;

}
