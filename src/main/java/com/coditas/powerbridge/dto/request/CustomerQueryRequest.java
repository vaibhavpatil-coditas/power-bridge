package com.coditas.powerbridge.dto.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Positive;
import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class CustomerQueryRequest {
    @Positive
    private Long customerId;
    @NotBlank
    private String query;
}
