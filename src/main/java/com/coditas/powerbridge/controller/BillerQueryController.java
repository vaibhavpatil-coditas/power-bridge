package com.coditas.powerbridge.controller;

import com.coditas.powerbridge.constants.ApiPaths;
import com.coditas.powerbridge.dto.request.BillerQueryRequest;
import com.coditas.powerbridge.dto.response.ApplicationResponse;
import com.coditas.powerbridge.dto.response.BillerQueryResponse;
import com.coditas.powerbridge.service.BillerQueryService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.net.URI;

@RestController
@RequestMapping(ApiPaths.BASE_PATH)
@RequiredArgsConstructor
public class BillerQueryController {

    private final BillerQueryService billerQueryService;

    @PostMapping(ApiPaths.BillerQuery.BILLER_QUERY)
    public ResponseEntity<ApplicationResponse<BillerQueryResponse>> raiseQuery(@Valid @RequestBody BillerQueryRequest request){
        BillerQueryResponse response =  billerQueryService.raiseQuery(request);
        URI location = URI.create(ApiPaths.BASE_PATH + ApiPaths.BillerQuery.BILLER_QUERY + "/" + response.getId());
        return ResponseEntity.created(location).body(ApplicationResponse.<BillerQueryResponse>builder()
                .success(true)
                .message("Biller query raised successfully")
                .data(response)
                .build());
    }
}
