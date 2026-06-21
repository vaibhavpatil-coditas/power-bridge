package com.coditas.powerbridge.service;

import com.coditas.powerbridge.dto.request.ElectricityBillRequest;
import com.coditas.powerbridge.dto.response.ElectricityBillResponse;
import jakarta.validation.Valid;

import java.util.List;

public interface ElectricityBillService {
    ElectricityBillResponse generateBill(Long customerId, @Valid ElectricityBillRequest request);

    List<ElectricityBillResponse> getAllBills();

    ElectricityBillResponse getBillByElectricityBillId(Long id);

    List<ElectricityBillResponse> getBillsByCustomerId(Long id);
}
