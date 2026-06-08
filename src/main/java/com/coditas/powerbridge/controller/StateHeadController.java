package com.coditas.powerbridge.controller;

import com.coditas.powerbridge.constants.ApiPaths;
import com.coditas.powerbridge.dto.request.UserRequest;
import com.coditas.powerbridge.dto.response.ApplicationResponse;
import com.coditas.powerbridge.dto.response.UserResponse;
import com.coditas.powerbridge.service.ManagementTeamMemberService;
import com.coditas.powerbridge.service.StateHeadService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.net.URI;

@RestController
@RequiredArgsConstructor
public class StateHeadController {

    private final StateHeadService stateHeadService;

    @PostMapping(ApiPaths.ManagementTeam.MANAGEMENT_TEAM_MEMBER + ApiPaths.StateHead.ONBOARD_STATE_HEAD)
    public ResponseEntity<ApplicationResponse<UserResponse>> onboardStateHead(@Valid @RequestBody UserRequest request){
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
