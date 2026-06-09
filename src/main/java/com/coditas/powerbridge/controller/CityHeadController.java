package com.coditas.powerbridge.controller;

import com.coditas.powerbridge.constants.ApiPaths;
import com.coditas.powerbridge.dto.request.UserRequest;
import com.coditas.powerbridge.dto.response.ApplicationResponse;
import com.coditas.powerbridge.dto.response.UserResponse;
import com.coditas.powerbridge.service.CityHeadService;
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
@RequestMapping(ApiPaths.CityHead.BASE)
@RequiredArgsConstructor
public class CityHeadController {

    private final CityHeadService cityHeadService;

    @PostMapping
    @PreAuthorize("hasRole('DISTRICT_HEAD')")
    public ResponseEntity<ApplicationResponse<UserResponse>> create(@Valid @RequestBody UserRequest request){
        UserResponse response = cityHeadService.create(request);
        URI location = URI.create(ApiPaths.CityHead.BASE
        +"/"+response.getId());
        return ResponseEntity.created(location).body(ApplicationResponse.<UserResponse>builder()
                .success(true)
                .message("City head created successfully")
                .data(response)
                .build());
    }
}
