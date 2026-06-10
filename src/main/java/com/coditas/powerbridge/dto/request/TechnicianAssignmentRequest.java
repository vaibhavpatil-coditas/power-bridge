package com.coditas.powerbridge.dto.request;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class TechnicianAssignmentRequest {
    @NotNull
    @Positive
    private Long technicianId;
}
