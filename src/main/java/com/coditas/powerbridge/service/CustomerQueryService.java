package com.coditas.powerbridge.service;

import com.coditas.powerbridge.dto.request.CustomerQueryRequest;
import com.coditas.powerbridge.dto.request.TenantRequest;
import com.coditas.powerbridge.dto.response.CustomerQueryResponse;
import jakarta.validation.Valid;

public interface CustomerQueryService {
    CustomerQueryResponse raiseQuery(@Valid CustomerQueryRequest request);

    CustomerQueryResponse resolveQuery(Long queryId, TenantRequest request);

    CustomerQueryResponse escalateToManager1(Long queryId, @Valid TenantRequest request);

    CustomerQueryResponse escalateToManager2(Long queryId);
}
