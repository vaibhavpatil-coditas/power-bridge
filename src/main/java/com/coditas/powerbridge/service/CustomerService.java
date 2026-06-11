package com.coditas.powerbridge.service;

import com.coditas.powerbridge.dto.request.CustomerRequest;
import com.coditas.powerbridge.dto.response.CustomerResponse;
import jakarta.validation.Valid;

public interface CustomerService {
    CustomerResponse create(@Valid CustomerRequest request);
}
