package com.coditas.powerbridge.controller;

import com.coditas.powerbridge.constants.ApiPaths;
import com.coditas.powerbridge.dto.request.UserRequest;
import com.coditas.powerbridge.dto.response.ApplicationResponse;
import com.coditas.powerbridge.dto.response.UserResponse;
import com.coditas.powerbridge.service.StateHeadService;
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
@RequestMapping(ApiPaths.StateHead.BASE)
@RequiredArgsConstructor
public class StateHeadController {

    private final StateHeadService stateHeadService;

    @PostMapping
    @PreAuthorize("hasRole('MANAGEMENT_TEAM_MEMBER')")
    public ResponseEntity<ApplicationResponse<UserResponse>> onboard(@Valid @RequestBody UserRequest request){
        UserResponse response = stateHeadService.onboard(request);

        URI location = URI.create(ApiPaths.StateHead.BASE
                +"/"+response.getId());
        return ResponseEntity.created(location)
                .body(ApplicationResponse.<UserResponse>builder()
                        .success(true)
                        .message("State head successfully onboarded")
                        .data(response)
                        .build());
    }
}
