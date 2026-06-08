package com.coditas.powerbridge.service;

import com.coditas.powerbridge.dto.request.StateHeadAssignmentRequest;
import com.coditas.powerbridge.dto.request.StateRequest;
import com.coditas.powerbridge.dto.response.StateResponse;
import jakarta.validation.Valid;

public interface StateService {
    StateResponse addState(@Valid StateRequest request);

    StateResponse assignStateHead(Long id, @Valid StateHeadAssignmentRequest request);
}
