package com.coditas.powerbridge.controller;

import com.coditas.powerbridge.constants.ApiPaths;
import com.coditas.powerbridge.dto.request.BillerQueryRequest;
import com.coditas.powerbridge.dto.response.ApplicationResponse;
import com.coditas.powerbridge.dto.response.BillerQueryResponse;
import com.coditas.powerbridge.service.BillerQueryService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;

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

    @GetMapping(ApiPaths.BillerQuery.BILLER_QUERY)
    public ResponseEntity<ApplicationResponse<List<BillerQueryResponse>>> getAllQueries(){
        List<BillerQueryResponse> response = billerQueryService.getAllQueries();
        return ResponseEntity.ok().body(ApplicationResponse.<List<BillerQueryResponse>>builder()
                .success(true)
                .message("Fetched all queries")
                .data(response)
                .build());
    }

    @GetMapping(ApiPaths.BillerQuery.BILLER_QUERY + ApiPaths.BillerQuery.ID)
    public ResponseEntity<ApplicationResponse<BillerQueryResponse>> getQueryByQueryId(@PathVariable(name = "query-id") Long queryId){
        BillerQueryResponse response = billerQueryService.getQueryByQueryId(queryId);
        return ResponseEntity.ok().body(ApplicationResponse.<BillerQueryResponse>builder()
                .success(true)
                .message("Fetched query by id")
                .data(response)
                .build());
    }

    @GetMapping(ApiPaths.Biller.BILLER + ApiPaths.Biller.ID + ApiPaths.BillerQuery.BILLER_QUERY)
    public ResponseEntity<ApplicationResponse<BillerQueryResponse>> getQueryByBillerId(@PathVariable(name = "biller-id") Long billerId){
        BillerQueryResponse response = billerQueryService.getQueryByBillerId(billerId);
        return ResponseEntity.ok().body(ApplicationResponse.<BillerQueryResponse>builder()
                .success(true)
                .message("Fetched all queries for biller")
                .data(response)
                .build());
    }


}
