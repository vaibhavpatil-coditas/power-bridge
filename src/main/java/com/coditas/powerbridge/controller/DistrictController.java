package com.coditas.powerbridge.controller;

import com.coditas.powerbridge.constants.ApiPaths;
import com.coditas.powerbridge.dto.request.DistrictHeadAssignmentRequest;
import com.coditas.powerbridge.dto.request.DistrictRequest;
import com.coditas.powerbridge.dto.response.ApplicationResponse;
import com.coditas.powerbridge.dto.response.DistrictResponse;
import com.coditas.powerbridge.service.DistrictService;
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
public class DistrictController {

    private final DistrictService districtService;

    @PostMapping(ApiPaths.District.BASE)
    @PreAuthorize("hasRole('STATE_HEAD')")
    public ResponseEntity<ApplicationResponse<DistrictResponse>> create(@Valid @RequestBody DistrictRequest request){
        DistrictResponse response = districtService.create(request);

        URI location = URI.create(ApiPaths.District.BASE
        +"/"+response.getId());

        return ResponseEntity.created(location).body(ApplicationResponse.<DistrictResponse>builder()
                    .success(true)
                    .message("District added successfully")
                    .data(response)
                    .build());
    }

    @GetMapping(ApiPaths.State.STATES + ApiPaths.State.ID + ApiPaths.District.BASE)
    @PreAuthorize("permitAll()")
    public ResponseEntity<ApplicationResponse<List<DistrictResponse>>> getAll(@PathVariable(name = "state-id") Long stateId){
        List<DistrictResponse> response = districtService.getAll(stateId);
        return ResponseEntity.ok().body(ApplicationResponse.<List<DistrictResponse>>builder()
                .success(true)
                .message("Fetched all the districts")
                .data(response)
                .build());
    }

    @PostMapping(ApiPaths.District.BASE + ApiPaths.District.ID+ApiPaths.District.HEAD)
    @PreAuthorize("hasRole('STATE_HEAD')")
    public ResponseEntity<ApplicationResponse<DistrictResponse>> assignDistrictHead(@PathVariable(required = true, name = "district-id") Long districtId,
                                                                                    @Valid @RequestBody DistrictHeadAssignmentRequest request){
        DistrictResponse response = districtService.assignDistrictHead(districtId, request);

        return ResponseEntity.ok().body(ApplicationResponse.<DistrictResponse>builder()
                        .success(true)
                        .message("District head successfullly assigned")
                        .data(response)
                        .build());
    }
}
