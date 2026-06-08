package com.coditas.powerbridge.dto.response;

import com.coditas.powerbridge.entity.State;
import com.coditas.powerbridge.entity.User;
import lombok.Getter;
import lombok.Setter;

import java.time.Instant;

@Getter @Setter
public class DistrictResponse {
    private Long id;
    private String name;
    private Instant createdAt;
    private State state;
    private User districtHead;
    private Instant assignedAt;
}
