package com.coditas.powerbridge.service;

import com.coditas.powerbridge.dto.request.BillerQueryRequest;
import com.coditas.powerbridge.dto.response.BillerQueryResponse;
import jakarta.validation.Valid;

import java.util.List;

public interface BillerQueryService {
    BillerQueryResponse raiseQuery(@Valid BillerQueryRequest request);

    List<BillerQueryResponse> getAllQueries();

    BillerQueryResponse getQueryByQueryId(Long queryId);

    BillerQueryResponse getQueryByBillerId(Long billerId);
}
