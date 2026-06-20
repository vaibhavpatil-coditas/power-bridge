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
import java.util.List;

@RestController
@RequestMapping(ApiPaths.BASE_PATH)
@RequiredArgsConstructor
public class AreaController {

    private final AreaService areaService;

    @PostMapping(ApiPaths.Area.BASE)
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

    @GetMapping(ApiPaths.Area.BASE)
    @PreAuthorize("permitAll()")
    public ResponseEntity<ApplicationResponse<List<AreaResponse>>> getAllInCity(@PathVariable(name = "city-id") Long cityId){
        List<AreaResponse> response = areaService.getAll(cityId);

        return ResponseEntity.ok().body(ApplicationResponse.<List<AreaResponse>>builder()
                .success(true)
                .message("Fetched all areas in the city")
                .data(response)
                .build());
    }

    @PutMapping(ApiPaths.Area.BASE + ApiPaths.Area.TECHNICIAN)
    @PreAuthorize("hasRole('CITY_HEAD')")
    public ResponseEntity<ApplicationResponse<AreaResponse>> assignTechnician(@PathVariable(name = "city-id") Long cityId,
                                                                              @PathVariable(name = "area-id") Long areaId,
                                                                              @Valid @RequestBody TechnicianAssignmentRequest request){
        AreaResponse response = areaService.assignTechnician(cityId, areaId, request);

        URI location = URI.create(ApiPaths.Area.TECHNICIAN.replace("{city-id}", cityId.toString()).replace("{area_id}", areaId.toString())
                +"/"+response.getId());

        return ResponseEntity.created(location).body(ApplicationResponse.<AreaResponse>builder()
                .success(true)
                .message("Technician assigned successfully")
                .data(response)
                .build());
    }

    @PutMapping(ApiPaths.Area.BASE + ApiPaths.Area.BILLER)
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
