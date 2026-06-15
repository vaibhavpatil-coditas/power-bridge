package com.coditas.powerbridge.service;

import com.coditas.powerbridge.dto.request.BillerQueryRequest;
import com.coditas.powerbridge.dto.response.BillerQueryResponse;
import jakarta.validation.Valid;

public interface BillerQueryService {
    BillerQueryResponse raiseQuery(@Valid BillerQueryRequest request);
}
