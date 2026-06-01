package com.coditas.powerbridge.dto.request;

import com.coditas.powerbridge.enums.Role;
import jakarta.validation.constraints.Email;
import lombok.Getter;
import lombok.Setter;

@Setter @Getter
public class UserRequest {
    private String name;

    @Email
    private String email;
    private String username;
    private String password;
    private Role role;
}
