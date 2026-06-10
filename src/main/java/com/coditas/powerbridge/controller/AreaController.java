package com.coditas.powerbridge.controller;

import com.coditas.powerbridge.constants.ApiPaths;
import com.coditas.powerbridge.dto.request.AreaRequest;
import com.coditas.powerbridge.dto.response.ApplicationResponse;
import com.coditas.powerbridge.dto.response.AreaResponse;
import com.coditas.powerbridge.service.AreaService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.net.URI;

@RestController
@RequestMapping(ApiPaths.Area.BASE)
@RequiredArgsConstructor
public class AreaController {

    private final AreaService areaService;

    @PostMapping
    @PreAuthorize("hasRole('CITY_HEAD')")
    public ResponseEntity<ApplicationResponse<AreaResponse>> create(@PathVariable(name = "city_id") Long cityId,
                                                                    @Valid @RequestBody AreaRequest request){

        AreaResponse response = areaService.create(cityId, request);

        URI location = URI.create(ApiPaths.Area.BASE.replace("{city_id}", cityId.toString())
                +"/"+response.getId());

        return ResponseEntity.created(location).body(ApplicationResponse.<AreaResponse>builder()
                .success(true)
                .message("Area created successfully")
                .data(response)
                .build());
    }
}
