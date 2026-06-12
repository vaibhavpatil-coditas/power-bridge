package com.coditas.powerbridge.controller;

import com.coditas.powerbridge.constants.ApiPaths;
import com.coditas.powerbridge.dto.request.CityHeadAssignmentRequest;
import com.coditas.powerbridge.dto.request.CityRequest;
import com.coditas.powerbridge.dto.response.ApplicationResponse;
import com.coditas.powerbridge.dto.response.CityResponse;
import com.coditas.powerbridge.service.CityService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.net.URI;

@RestController
@RequestMapping(ApiPaths.City.BASE)
@RequiredArgsConstructor
public class CityController {

    private final CityService cityService;

    @PostMapping
    @PreAuthorize("hasRole('DISTRICT_HEAD')")
    public ResponseEntity<ApplicationResponse<CityResponse>> create(@PathVariable(name = "district-id") Long districtId,
                                                                    @Valid @RequestBody CityRequest request){

        CityResponse response = cityService.create(districtId, request);

        URI location = URI.create(ApiPaths.City.BASE.replace("{district_id}", districtId.toString())
        +"/"+response.getId());

        return ResponseEntity.created(location).body(ApplicationResponse.<CityResponse>builder()
                .success(true)
                .message("City added successfully")
                .data(response)
                .build());
    }

    @PutMapping(ApiPaths.City.ID + ApiPaths.City.HEAD)
    public ResponseEntity<ApplicationResponse<CityResponse>> assignHead(@PathVariable(name = "district-id") Long districtId,
                                                                        @PathVariable(name = "city-id") Long cityId,
                                                                        @Valid @RequestBody CityHeadAssignmentRequest request){

        CityResponse response = cityService.assignHead(cityId, request);

        return ResponseEntity.ok().body(ApplicationResponse.<CityResponse>builder()
                .success(true)
                .message("City head assigned successfully")
                .data(response)
                .build());
    }
}
