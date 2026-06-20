package com.coditas.powerbridge.dto.response;

import lombok.Getter;
import lombok.Setter;

import java.time.Instant;

@Getter @Setter
public class StateResponse {
    private Long id;
    private String name;
    private Instant createdAt;
    private Long stateHeadId;
    private Instant assignedAt;
}
