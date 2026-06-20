package com.coditas.powerbridge.service;

import com.coditas.powerbridge.dto.request.StateHeadAssignmentRequest;
import com.coditas.powerbridge.dto.request.StateRequest;
import com.coditas.powerbridge.dto.response.StateResponse;
import jakarta.validation.Valid;

import java.util.List;

public interface StateService {
    StateResponse create(@Valid StateRequest request);

    StateResponse assignStateHead(Long stateId, @Valid StateHeadAssignmentRequest request);

    List<StateResponse> getAll();
}
