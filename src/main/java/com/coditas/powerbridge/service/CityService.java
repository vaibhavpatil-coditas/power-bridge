package com.coditas.powerbridge.service;

import com.coditas.powerbridge.dto.request.CityHeadAssignmentRequest;
import com.coditas.powerbridge.dto.request.CityRequest;
import com.coditas.powerbridge.dto.response.CityResponse;
import jakarta.validation.Valid;

import java.util.List;

public interface CityService {
    CityResponse create(Long districtId, @Valid CityRequest request);

    CityResponse assignHead(Long cityId, @Valid CityHeadAssignmentRequest request);

    List<CityResponse> getAll(long districtId);

    CityResponse getCityById(long cityId);
}
