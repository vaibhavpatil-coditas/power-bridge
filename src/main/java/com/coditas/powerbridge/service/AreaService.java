package com.coditas.powerbridge.service;

import com.coditas.powerbridge.dto.request.AreaRequest;
import com.coditas.powerbridge.dto.request.BillerAssignmentRequest;
import com.coditas.powerbridge.dto.request.TechnicianAssignmentRequest;
import com.coditas.powerbridge.dto.response.AreaResponse;
import jakarta.validation.Valid;

import java.util.List;

public interface AreaService {
    AreaResponse create(Long cityId, @Valid AreaRequest request);

    AreaResponse assignTechnician(Long cityId, Long areaId, @Valid TechnicianAssignmentRequest request);

    AreaResponse assignBiller(Long cityId, Long areaId, @Valid BillerAssignmentRequest request);

    List<AreaResponse> getAll(Long cityId);

    AreaResponse getAreaById(Long cityId, Long areaId);
}
