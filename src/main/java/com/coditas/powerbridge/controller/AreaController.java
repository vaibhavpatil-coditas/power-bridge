package com.coditas.powerbridge.controller;

import com.coditas.powerbridge.constants.ApiPaths;
import com.coditas.powerbridge.dto.request.AreaRequest;
import com.coditas.powerbridge.dto.request.BillerAssignmentRequest;
import com.coditas.powerbridge.dto.request.TechnicianAssignmentRequest;
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
    public ResponseEntity<ApplicationResponse<AreaResponse>> create(@PathVariable(name = "city-id") Long cityId,
                                                                    @Valid @RequestBody AreaRequest request){

        AreaResponse response = areaService.create(cityId, request);

        URI location = URI.create(ApiPaths.Area.BASE.replace("{city-id}", cityId.toString())
                +"/"+response.getId());

        return ResponseEntity.created(location).body(ApplicationResponse.<AreaResponse>builder()
                .success(true)
                .message("Area created successfully")
                .data(response)
                .build());
    }

    @PutMapping(ApiPaths.Area.TECHNICIAN)
    @PreAuthorize("hasRole('CITY_HEAD')")
    public ResponseEntity<ApplicationResponse<AreaResponse>> assignTechnician(@PathVariable(name = "city-id") Long cityId,
                                                                              @PathVariable(name = "area-id") Long areaId,
                                                                              @Valid @RequestBody TechnicianAssignmentRequest request){
        AreaResponse response = areaService.assignTechnician(cityId, areaId, request);

        URI location = URI.create(ApiPaths.Area.TECHNICIAN.replace(ApiPaths.City.ID, cityId.toString()).replace("{area_id}", areaId.toString())
                +"/"+response.getId());

        return ResponseEntity.created(location).body(ApplicationResponse.<AreaResponse>builder()
                .success(true)
                .message("Technician assigned successfully")
                .data(response)
                .build());
    }

    @PutMapping(ApiPaths.Area.BILLER)
    @PreAuthorize("hasRole('CITY_HEAD')")
    public ResponseEntity<ApplicationResponse<AreaResponse>> assignBiller(@PathVariable(name = "city-id") Long cityId,
                                                                              @PathVariable(name = "area-id") Long areaId,
                                                                              @Valid @RequestBody BillerAssignmentRequest request){
        AreaResponse response = areaService.assignBiller(cityId, areaId, request);

        URI location = URI.create(ApiPaths.Area.BILLER.replace(ApiPaths.City.ID, cityId.toString()).replace("{area-id}", areaId.toString())
                +"/"+response.getId());

        return ResponseEntity.created(location).body(ApplicationResponse.<AreaResponse>builder()
                .success(true)
                .message("Technician assigned successfully")
                .data(response)
                .build());
    }
}
