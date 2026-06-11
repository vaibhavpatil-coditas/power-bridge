package com.coditas.powerbridge.controller;

import com.coditas.powerbridge.constants.ApiPaths;
import com.coditas.powerbridge.dto.request.UserRequest;
import com.coditas.powerbridge.dto.response.ApplicationResponse;
import com.coditas.powerbridge.dto.response.UserResponse;
import com.coditas.powerbridge.service.LocalTechnicianService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.net.URI;

@RestController
@RequestMapping(ApiPaths.LocalTechnician.BASE)
@RequiredArgsConstructor
public class LocalTechnicianController {

    private final LocalTechnicianService localTechnicianService;

    @PostMapping
    @PreAuthorize("hasRole('CITY_HEAD')")
    public ResponseEntity<ApplicationResponse<UserResponse>> create(@PathVariable(name = "city-id") Long cityId,
            @Valid @RequestBody UserRequest request){

        UserResponse response = localTechnicianService.create(request);

        URI location = URI.create(ApiPaths.LocalTechnician.BASE.replace("{city_id}", cityId.toString())
                +"/"+response.getId());

        return ResponseEntity.created(location).body(ApplicationResponse.<UserResponse>builder()
                .success(true)
                .message("Local technician created")
                .data(response)
                .build());
    }
}
