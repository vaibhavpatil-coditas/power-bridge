package com.coditas.powerbridge.dto.response;

import com.coditas.powerbridge.enums.BillStatus;
import lombok.Getter;
import lombok.Setter;

import java.time.Instant;

@Getter @Setter
public class ElectricityBillResponse {
    private Long id;
    private Long customerId;
    private double amount;
    private Instant generatedAt;
    private Instant dueDate;
    private BillStatus status;
}
