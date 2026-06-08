package com.coditas.powerbridge.controller;

import com.coditas.powerbridge.constants.ApiPaths;
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
    public ResponseEntity<ApplicationResponse<DistrictResponse>> create(@PathVariable(name = "id") Long stateId, @Valid @RequestBody DistrictRequest request){
        DistrictResponse response = districtService.create(stateId, request);

        URI location = URI.create(ApiPaths.District.BASE
        +"/"+response.getId());

        return ResponseEntity.created(location).body(ApplicationResponse.<DistrictResponse>builder()
                    .success(true)
                    .message("District added successfully")
                    .data(response)
                    .build());
    }
}
