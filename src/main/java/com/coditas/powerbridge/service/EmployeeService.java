package com.coditas.powerbridge.service;

import com.coditas.powerbridge.dto.request.EmployeeRequest;
import com.coditas.powerbridge.dto.response.EmployeeResponse;
import jakarta.validation.Valid;

public interface EmployeeService {
    EmployeeResponse onboardSalesTeamMember(@Valid EmployeeRequest request);

    EmployeeResponse onboardOperationsHead(@Valid EmployeeRequest request);

    EmployeeResponse onboardBPO(@Valid EmployeeRequest request);
}
