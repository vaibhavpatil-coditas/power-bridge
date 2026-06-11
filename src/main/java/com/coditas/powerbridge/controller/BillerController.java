package com.coditas.powerbridge.controller;

import com.coditas.powerbridge.constants.ApiPaths;
import com.coditas.powerbridge.dto.request.UserRequest;
import com.coditas.powerbridge.dto.response.ApplicationResponse;
import com.coditas.powerbridge.dto.response.UserResponse;
import com.coditas.powerbridge.service.BillerService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.net.URI;

@RestController
@RequestMapping(ApiPaths.Biller.BASE)
@RequiredArgsConstructor
public class BillerController {

    private final BillerService billerService;

    @PostMapping
    @PreAuthorize("hasRole('CITY_HEAD')")
    public ResponseEntity<ApplicationResponse<UserResponse>> create(@PathVariable(name = "city-id") Long cityId,
                                                                    @Valid @RequestBody UserRequest userRequest){

        UserResponse response = billerService.create(userRequest);

        URI location = URI.create(ApiPaths.Biller.BASE.replace("{city-id}", cityId.toString())
                +"/"+cityId);

        return ResponseEntity.created(location).body(ApplicationResponse.<UserResponse>builder()
                .success(true)
                .message("Biller created successfully")
                .data(response)
                .build());
    }
}
