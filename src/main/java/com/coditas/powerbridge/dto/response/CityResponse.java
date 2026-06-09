package com.coditas.powerbridge.dto.response;

import lombok.Getter;
import lombok.Setter;

import java.time.Instant;

@Getter @Setter
public class CityResponse {
    private Long id;
    private String name;
    private Instant createdAt;
    private DistrictResponse district;
    private UserResponse cityHead;
    private Instant assignedAt;
}
