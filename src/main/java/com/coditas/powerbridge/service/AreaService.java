package com.coditas.powerbridge.service;

import com.coditas.powerbridge.dto.request.AreaRequest;
import com.coditas.powerbridge.dto.request.TechnicianAssignmentRequest;
import com.coditas.powerbridge.dto.response.AreaResponse;
import jakarta.validation.Valid;

public interface AreaService {
    AreaResponse create(Long cityId, @Valid AreaRequest request);

    AreaResponse assignTechnician(Long cityId, Long areaId, @Valid TechnicianAssignmentRequest request);
}
