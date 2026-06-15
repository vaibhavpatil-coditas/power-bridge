package com.coditas.powerbridge.dto.response;

import com.coditas.powerbridge.entity.Customer;
import lombok.Getter;
import lombok.Setter;

import java.time.Instant;

@Getter @Setter
public class ElectricityBillResponse {
    private Long id;
    private Customer customer;
    private double amount;
    private Instant generatedAt;
    private Instant dueDate;
}
