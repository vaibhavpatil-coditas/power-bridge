package com.coditas.powerbridge.controller;

import com.coditas.powerbridge.constants.ApiPaths;
import com.coditas.powerbridge.dto.request.ElectricityBillRequest;
import com.coditas.powerbridge.dto.response.ApplicationResponse;
import com.coditas.powerbridge.dto.response.ElectricityBillResponse;
import com.coditas.powerbridge.service.ElectricityBillService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.net.URI;

@RestController
@RequiredArgsConstructor
@RequestMapping(ApiPaths.BASE_PATH)
public class ElectricityBillController {

    private final ElectricityBillService electricityBillService;

    @PreAuthorize("hasRole('BILLER')")
    @PostMapping(ApiPaths.Customer.CUSTOMERS + ApiPaths.Customer.ID + ApiPaths.ElectricityBill.ELECTRICITY_BILL)
    public ResponseEntity<ApplicationResponse<ElectricityBillResponse>> generateBill(@PathVariable(name = "customer-id") Long customerId,
                                                                                     @Valid @RequestBody ElectricityBillRequest request){
        ElectricityBillResponse response = electricityBillService.generateBill(customerId, request);
        URI location = URI.create(ApiPaths.ElectricityBill.ELECTRICITY_BILL
                +"/"+response.getId());
        return ResponseEntity.created(location).body(ApplicationResponse.<ElectricityBillResponse>builder()
                .success(true)
                .message("Bill generated successfully")
                .data(response)
                .build());
    }
}
