package com.coditas.powerbridge.controller;

import com.coditas.powerbridge.constants.ApiPaths;
import com.coditas.powerbridge.dto.request.UserRequest;
import com.coditas.powerbridge.dto.response.ApplicationResponse;
import com.coditas.powerbridge.dto.response.UserResponse;
import com.coditas.powerbridge.service.DistrictHeadService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.net.URI;

@RestController
@RequiredArgsConstructor
@RequestMapping(ApiPaths.DistrictHead.BASE)
public class DistrictHeadController {

    private final DistrictHeadService districtHeadService;

    @PostMapping
    @PreAuthorize("hasRole('STATE_HEAD')")
    public ResponseEntity<ApplicationResponse<UserResponse>> create(@Valid @RequestBody UserRequest request){
        UserResponse response = districtHeadService.create(request);

        URI location = URI.create(ApiPaths.DistrictHead.BASE
                +"/"+response.getId());

        return ResponseEntity.created(location).body(ApplicationResponse.<UserResponse>builder()
                        .success(true)
                        .message("District head created successfully")
                        .data(response)
                        .build());
    }
}
