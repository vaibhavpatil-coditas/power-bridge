package com.coditas.powerbridge.controller;

import com.coditas.powerbridge.constants.ApiPaths;
import com.coditas.powerbridge.dto.request.MeterServiceProviderRequest;
import com.coditas.powerbridge.dto.request.ServiceProviderRequest;
import com.coditas.powerbridge.dto.response.ApplicationResponse;
import com.coditas.powerbridge.dto.response.MeterServiceProviderResponse;
import com.coditas.powerbridge.dto.response.ServiceProviderResponse;
import com.coditas.powerbridge.service.ServiceProviderService;
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
@RequestMapping(ApiPaths.BASE_PATH)
@RequiredArgsConstructor
public class ServiceProviderController {

    private final ServiceProviderService serviceProviderService;

    @PostMapping(ApiPaths.ServiceProvider.BASE)
    @PreAuthorize("hasRole('SALES_TEAM_MEMBER')")
    public ResponseEntity<ApplicationResponse<ServiceProviderResponse>> onboard(@Valid @RequestBody ServiceProviderRequest request){
        return ResponseEntity.created(URI.create(ApiPaths.SalesTeamMember.BASE+ApiPaths.ON_BOARD))
                .body(
                        ApplicationResponse.<ServiceProviderResponse>builder()
                                .success(true)
                                .message("Service provider successfully registered")
                                .data(serviceProviderService.onboard(request))
                                .build()
                );
    }

    @PostMapping(ApiPaths.ServiceProvider.METER)
    @PreAuthorize("hasRole('OPERATIONS_HEAD')")
    public ResponseEntity<ApplicationResponse<MeterServiceProviderResponse>> create(@Valid @RequestBody MeterServiceProviderRequest request){
        MeterServiceProviderResponse response = serviceProviderService.create(request);

        URI location = URI.create(ApiPaths.BASE_PATH + ApiPaths.ServiceProvider.METER + response.getServiceProvider().getId());

        return ResponseEntity.created(location).body(ApplicationResponse.<MeterServiceProviderResponse>builder()
                .success(true)
                .message("Added meters for service provider")
                .data(response)
                .build());
    }
}
