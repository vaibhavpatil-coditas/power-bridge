package com.coditas.powerbridge.controller;

import com.coditas.powerbridge.constants.ApiPaths;
import com.coditas.powerbridge.dto.request.DistrictHeadAssignmentRequest;
import com.coditas.powerbridge.dto.request.DistrictRequest;
import com.coditas.powerbridge.dto.response.ApplicationResponse;
import com.coditas.powerbridge.dto.response.DistrictResponse;
import com.coditas.powerbridge.service.DistrictService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.net.URI;

@RestController
@RequestMapping(ApiPaths.District.BASE)
@RequiredArgsConstructor
public class DistrictController {

    private final DistrictService districtService;

    @PostMapping
    @PreAuthorize("hasRole('STATE_HEAD')")
    public ResponseEntity<ApplicationResponse<DistrictResponse>> create(@Valid @RequestBody DistrictRequest request){
        DistrictResponse response = districtService.create(request);

        URI location = URI.create(ApiPaths.District.BASE
        +"/"+response.getId());

        return ResponseEntity.created(location).body(ApplicationResponse.<DistrictResponse>builder()
                    .success(true)
                    .message("District added successfully")
                    .data(response)
                    .build());
    }

    @PostMapping(ApiPaths.District.ID+ApiPaths.District.HEAD)
    @PreAuthorize("hasRole('STATE_HEAD')")
    public ResponseEntity<ApplicationResponse<DistrictResponse>> assignDistrictHead(@PathVariable(required = true, name = "district-id") Long districtId,
                                                                                    @Valid @RequestBody DistrictHeadAssignmentRequest request){
        DistrictResponse response = districtService.assignDistrictHead(districtId, request);

        return ResponseEntity.ok().body(ApplicationResponse.<DistrictResponse>builder()
                        .success(true)
                        .message("District head successfullly assigned")
                        .data(response)
                        .build());
    }
}
