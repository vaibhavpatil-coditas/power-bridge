package com.coditas.powerbridge.controller;

import com.coditas.powerbridge.constants.ApiPaths;
import com.coditas.powerbridge.dto.request.ServiceProviderRequest;
import com.coditas.powerbridge.dto.response.ApplicationResponse;
import com.coditas.powerbridge.dto.response.ServiceProviderResponse;
import com.coditas.powerbridge.service.ServiceProviderService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.net.URI;

@RestController
@RequiredArgsConstructor
public class ServiceProviderController {

    private final ServiceProviderService serviceProviderService;

    @PreAuthorize("hasRole('SALES_TEAM_MEMBER')")
    @PostMapping(ApiPaths.SalesTeam.BASE + ApiPaths.ServiceProvider.ONBOARD_SERVICE_PROVIDER)
    public ResponseEntity<ApplicationResponse<ServiceProviderResponse>> onboard(@Valid @RequestBody ServiceProviderRequest request){
        return ResponseEntity.created(URI.create(ApiPaths.SalesTeam.BASE+ApiPaths.ON_BOARD))
                .body(
                        ApplicationResponse.<ServiceProviderResponse>builder()
                                .success(true)
                                .message("Service provider successfully registered")
                                .data(serviceProviderService.onboard(request))
                                .build()
                );
    }
}
