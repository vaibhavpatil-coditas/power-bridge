package com.coditas.powerbridge.dto.request;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.Getter;
import lombok.Setter;

import java.time.Instant;

@Getter @Setter
public class ElectricityBillRequest {
    @Positive
    private double amount;
    @NotNull
    private Instant dueDate;
}
