package com.coditas.powerbridge.controller;

import com.coditas.powerbridge.constants.ApiPaths;
import com.coditas.powerbridge.dto.request.ServiceProviderRequest;
import com.coditas.powerbridge.dto.request.UserRequest;
import com.coditas.powerbridge.dto.response.ApplicationResponse;
import com.coditas.powerbridge.dto.response.ServiceProviderResponse;
import com.coditas.powerbridge.dto.response.UserResponse;
import com.coditas.powerbridge.service.ManagementTeamMemberService;
import com.coditas.powerbridge.service.SalesTeamMemberService;
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
public class SalesTeamMemberController {

    private final SalesTeamMemberService salesTeamMemberService;

    @PreAuthorize("hasRole('MANAGEMENT_TEAM_MEMBER')")
    @PostMapping(ApiPaths.ManagementTeam.BASE + ApiPaths.SalesTeam.ONBOARD_SALES_TEAM_MEMBER)
    public ResponseEntity<ApplicationResponse<UserResponse>> onboard(@Valid @RequestBody UserRequest userRequest) {
        UserResponse userResponse = salesTeamMemberService.onboard(userRequest);

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
}
