package com.coditas.powerbridge.controller;

import com.coditas.powerbridge.constants.ApiPaths;
import com.coditas.powerbridge.dto.request.UserRequest;
import com.coditas.powerbridge.dto.response.ApplicationResponse;
import com.coditas.powerbridge.dto.response.UserResponse;
import com.coditas.powerbridge.service.UserService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.net.URI;

@RestController
@RequestMapping(ApiPaths.SuperAdmin.SUPER_ADMIN_BASE)
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    @PostMapping(ApiPaths.SuperAdmin.ONBOARD_MANAGEMENT_TEAM_MEMBER)
    public ResponseEntity<ApplicationResponse<UserResponse>> onboardManagementTeamMember(@Valid @RequestBody UserRequest userRequest){
        UserResponse userResponse = userService.onboardManagementTeamMember(userRequest);

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
