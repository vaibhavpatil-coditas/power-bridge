package com.coditas.powerbridge.controller;

import com.coditas.powerbridge.constants.ApiPaths;
import com.coditas.powerbridge.dto.request.StateHeadAssignmentRequest;
import com.coditas.powerbridge.dto.request.StateRequest;
import com.coditas.powerbridge.dto.response.ApplicationResponse;
import com.coditas.powerbridge.dto.response.StateResponse;
import com.coditas.powerbridge.service.StateService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.net.URI;

@RestController
@RequestMapping(ApiPaths.BASE_PATH + ApiPaths.State.STATES)
@RequiredArgsConstructor
public class StateController {

    private final StateService stateService;

    @PostMapping
    @PreAuthorize("hasRole('SUPER_ADMIN')")
    public ResponseEntity<ApplicationResponse<StateResponse>> create(@Valid @RequestBody StateRequest request){
        StateResponse response = stateService.create(request);

        URI location = URI.create(ApiPaths.BASE_PATH + ApiPaths.State.STATES
        +"/"+response.getId());

        return ResponseEntity.created(location).body(ApplicationResponse.<StateResponse>builder()
                .success(true)
                .message("State added successfully")
                .data(response)
                .build());
    }

    @PutMapping(ApiPaths.State.ID + ApiPaths.State.HEAD)
    @PreAuthorize("hasRole('MANAGEMENT_TEAM_MEMBER')")
    public ResponseEntity<ApplicationResponse<StateResponse>> assignStateHead(@PathVariable(name = "state-id") Long stateId,
                                                                              @Valid @RequestBody(required = true) StateHeadAssignmentRequest request){
        StateResponse response = stateService.assignStateHead(stateId, request);

        return ResponseEntity.ok().body(ApplicationResponse.<StateResponse>builder()
                .success(true)
                .message("State head assigned successfully")
                .data(response)
                .build());
    }
}
