package com.coditas.powerbridge.dto.response;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.time.Instant;

@Builder
@Getter @Setter
public class ServiceProviderResponse {
    private Long id;
    private String name;
    private UserResponse user;
    private Instant createdAt;
}