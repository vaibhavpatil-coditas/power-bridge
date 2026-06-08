package com.coditas.powerbridge.controller;

import com.coditas.powerbridge.constants.ApiPaths;
import com.coditas.powerbridge.dto.request.ServiceProviderRequest;
import com.coditas.powerbridge.dto.response.ApplicationResponse;
import com.coditas.powerbridge.dto.response.ServiceProviderResponse;
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
@RequestMapping(ApiPaths.SalesTeam.BASE)
@RequiredArgsConstructor
@PreAuthorize("hasRole('SALES_TEAM_MEMBER')")
public class SalesTeamMemberController {

    private final SalesTeamMemberService salesTeamMemberService;

    @PostMapping(ApiPaths.SalesTeam.ONBOARD_SERVICE_PROVIDER)
    public ResponseEntity<ApplicationResponse<ServiceProviderResponse>> onboard(@Valid @RequestBody ServiceProviderRequest request){
        return ResponseEntity.created(URI.create(ApiPaths.SalesTeam.BASE+ApiPaths.ON_BOARD))
                .body(
                        ApplicationResponse.<ServiceProviderResponse>builder()
                                .success(true)
                                .message("Service provider successfully registered")
                                .data(salesTeamMemberService.onboard(request))
                                .build()
                );
    }
}
