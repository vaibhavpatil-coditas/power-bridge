package com.coditas.powerbridge.controller;

import com.coditas.powerbridge.constants.ApiPaths;
import com.coditas.powerbridge.dto.request.UserRequest;
import com.coditas.powerbridge.dto.response.ApplicationResponse;
import com.coditas.powerbridge.dto.response.UserResponse;
import com.coditas.powerbridge.service.ManagementTeamMemberService;
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
@RequestMapping(ApiPaths.ManagementTeam.BASE)
@RequiredArgsConstructor
@PreAuthorize("hasRole('MANAGEMENT_TEAM_MEMBER')")
public class ManagementTeamMemberController {

    private final ManagementTeamMemberService managementTeamMemberService;

    @PostMapping(ApiPaths.ManagementTeam.ONBOARD_SALES_TEAM_MEMBER)
    public ResponseEntity<ApplicationResponse<UserResponse>> onboardSalesTeamMember(@Valid @RequestBody UserRequest userRequest) {
        UserResponse userResponse = managementTeamMemberService.onboardSalesTeamMember(userRequest);

        URI location = URI.create(ApiPaths.SalesTeam.BASE
                + "/"
                + userResponse.getId());

        return ResponseEntity.created(location)
                .body(ApplicationResponse.<UserResponse>builder()
                        .success(true)
                        .message("Sales team member successfully onboarded")
                        .data(userResponse)
                        .build());
    }

    @PostMapping(ApiPaths.ManagementTeam.ONBOARD_STATE_HEAD)
    public ResponseEntity<ApplicationResponse<UserResponse>> onboardStatehead(@Valid @RequestBody UserRequest request){
        UserResponse response = managementTeamMemberService.onboardStatehead(request);

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
