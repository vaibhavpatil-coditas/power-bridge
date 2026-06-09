package com.coditas.powerbridge.service;

import com.coditas.powerbridge.dto.request.CityRequest;
import com.coditas.powerbridge.dto.response.CityResponse;
import jakarta.validation.Valid;

public interface CityService {
    CityResponse create(Long districtId, @Valid CityRequest request);
}
