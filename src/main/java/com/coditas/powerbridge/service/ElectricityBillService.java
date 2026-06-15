package com.coditas.powerbridge.service;

import com.coditas.powerbridge.dto.request.ElectricityBillRequest;
import com.coditas.powerbridge.dto.response.ElectricityBillResponse;
import jakarta.validation.Valid;

public interface ElectricityBillService {
    ElectricityBillResponse generateBill(Long customerId, @Valid ElectricityBillRequest request);
}
