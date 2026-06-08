package com.coditas.powerbridge.service;

import com.coditas.powerbridge.dto.request.DistrictRequest;
import com.coditas.powerbridge.dto.response.DistrictResponse;
import jakarta.validation.Valid;

public interface DistrictService {
    DistrictResponse create(Long stateId, @Valid DistrictRequest request);
}
