package com.coditas.powerbridge.dto.request;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class DistrictHeadAssignmentRequest {
    @NotNull
    @Positive
    private Long districtHeadId;
    @NotNull
    @Positive
    private Long stateId;
}
