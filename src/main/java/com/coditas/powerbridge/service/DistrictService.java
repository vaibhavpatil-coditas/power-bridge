package com.coditas.powerbridge.service;

import com.coditas.powerbridge.dto.request.DistrictHeadAssignmentRequest;
import com.coditas.powerbridge.dto.request.DistrictRequest;
import com.coditas.powerbridge.dto.response.DistrictResponse;
import jakarta.validation.Valid;

import java.util.List;

public interface DistrictService {
    DistrictResponse create(@Valid DistrictRequest request);

    DistrictResponse assignDistrictHead(Long districtId, @Valid DistrictHeadAssignmentRequest request);

    List<DistrictResponse> getAll(Long stateId);

    DistrictResponse getDistrictById(Long districtId);
}
