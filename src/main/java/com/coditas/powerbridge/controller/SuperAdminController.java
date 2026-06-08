package com.coditas.powerbridge.controller;

import com.coditas.powerbridge.constants.ApiPaths;
import com.coditas.powerbridge.dto.request.UserRequest;
import com.coditas.powerbridge.dto.response.ApplicationResponse;
import com.coditas.powerbridge.dto.response.UserResponse;
import com.coditas.powerbridge.service.SuperAdminService;
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
@RequestMapping(ApiPaths.SuperAdmin.BASE)
public class SuperAdminController {

    private final SuperAdminService superAdminService;

    @PostMapping(ApiPaths.SuperAdmin.ONBOARD_MANAGEMENT_TEAM_MEMBER)
    @PreAuthorize("hasRole('SUPER_ADMIN')")
    public ResponseEntity<ApplicationResponse<UserResponse>> onboardManagementTeamMember(@Valid @RequestBody UserRequest userRequest){
        UserResponse userResponse = superAdminService.onboardManagementTeamMember(userRequest);

        URI location = URI.create(ApiPaths.ManagementTeam.BASE
                +"/"
                +userResponse.getId());

        return ResponseEntity.created(location)
                .body(ApplicationResponse.<UserResponse>builder()
                        .success(true)
                        .message("Management team member is added successfully")
                        .data(userResponse)
                        .build());
    }
}
