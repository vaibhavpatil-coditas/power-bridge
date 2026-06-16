package com.coditas.powerbridge.dto.response;

import com.coditas.powerbridge.enums.Role;
import lombok.Getter;
import lombok.Setter;

import java.time.Instant;

@Getter @Setter
public class EmployeeResponse {
    private Long id;
    private String name;
    private String email;
    private Role role;
    private Instant createdAt;
}
