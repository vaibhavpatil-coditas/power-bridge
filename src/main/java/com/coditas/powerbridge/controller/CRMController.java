package com.coditas.powerbridge.controller;

import com.coditas.powerbridge.constants.ApiPaths;
import com.coditas.powerbridge.dto.request.UserRequest;
import com.coditas.powerbridge.dto.response.ApplicationResponse;
import com.coditas.powerbridge.dto.response.UserResponse;
import com.coditas.powerbridge.service.CRMService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.net.URI;

@RestController
@RequestMapping(ApiPaths.CRM.BASE)
@RequiredArgsConstructor
public class CRMController {

    private final CRMService crmService;

    @PostMapping
    @PreAuthorize("hasRole('CITY_HEAD')")
    public ResponseEntity<ApplicationResponse<UserResponse>> create(@PathVariable(name = "city_id") Long cityId,
                                                                    @Valid @RequestBody UserRequest request){

        UserResponse response = crmService.create(request);

        URI location = URI.create(ApiPaths.CRM.BASE.replace("{city_id}", cityId.toString())
                +"/"+cityId);

        return ResponseEntity.created(location).body(ApplicationResponse.<UserResponse>builder()
                        .success(true)
                        .message("CRM added successfully")
                        .data(response)
                        .build());
    }
}
