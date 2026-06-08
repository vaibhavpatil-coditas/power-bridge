package com.coditas.powerbridge.service;

import com.coditas.powerbridge.dto.request.ServiceProviderRequest;
import com.coditas.powerbridge.dto.response.ServiceProviderResponse;
import jakarta.validation.Valid;

public interface ServiceProviderService {
    ServiceProviderResponse onboard(@Valid ServiceProviderRequest request);
}
