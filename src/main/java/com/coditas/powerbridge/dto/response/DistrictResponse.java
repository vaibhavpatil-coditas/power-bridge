package com.coditas.powerbridge.dto.response;

import lombok.Getter;
import lombok.Setter;

import java.time.Instant;

@Getter @Setter
public class DistrictResponse {
    private Long id;
    private String name;
    private Instant createdAt;
    private Long stateId;
    private Long districtHeadId;
    private Instant assignedAt;
}
