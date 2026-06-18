package com.coditas.powerbridge.service;

import com.coditas.powerbridge.dto.request.CustomerQueryRequest;
import com.coditas.powerbridge.dto.response.CustomerQueryResponse;
import jakarta.validation.Valid;

public interface CustomerQueryService {
    CustomerQueryResponse raiseQuery(@Valid CustomerQueryRequest request);
}
