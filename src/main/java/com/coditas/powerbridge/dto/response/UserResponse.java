package com.coditas.powerbridge.dto.response;

import com.coditas.powerbridge.enums.Role;
import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class UserResponse {
    private Long id;
    private String name;
    private String email;
    private String username;
    private Role role;
}
