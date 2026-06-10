package com.coditas.powerbridge.service;

import com.coditas.powerbridge.dto.request.AreaRequest;
import com.coditas.powerbridge.dto.response.AreaResponse;
import jakarta.validation.Valid;

public interface AreaService {
    AreaResponse create(Long cityId, @Valid AreaRequest request);
}
