package com.coditas.powerbridge.service;

import com.coditas.powerbridge.dto.request.CustomerRequest;
import com.coditas.powerbridge.dto.request.UserServiceProviderRequest;
import com.coditas.powerbridge.dto.response.CustomerResponse;
import com.coditas.powerbridge.dto.response.UserServiceProviderReponse;
import jakarta.validation.Valid;

public interface CustomerService {
    CustomerResponse create(@Valid CustomerRequest request);

    UserServiceProviderReponse assignServiceProvider(@Valid UserServiceProviderRequest request);
}
