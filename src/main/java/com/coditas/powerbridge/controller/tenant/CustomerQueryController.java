package com.coditas.powerbridge.controller.tenant;

import com.coditas.powerbridge.constants.ApiPaths;
import com.coditas.powerbridge.dto.request.CustomerQueryRequest;
import com.coditas.powerbridge.dto.request.TenantRequest;
import com.coditas.powerbridge.dto.response.ApplicationResponse;
import com.coditas.powerbridge.dto.response.CustomerQueryResponse;
import com.coditas.powerbridge.service.CustomerQueryService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.net.URI;

@RestController
@RequiredArgsConstructor
@RequestMapping(ApiPaths.BASE_PATH)
public class CustomerQueryController {

    private final CustomerQueryService customerQueryService;

    @PreAuthorize("hasRole('BPO')")
    @PostMapping(ApiPaths.Customer.CUSTOMER_QUERY)
    public ResponseEntity<ApplicationResponse<CustomerQueryResponse>> raiseQuery(@Valid @RequestBody CustomerQueryRequest request){
        CustomerQueryResponse response = customerQueryService.raiseQuery(request);

        URI location = URI.create(ApiPaths.BASE_PATH + ApiPaths.Customer.CUSTOMER_QUERY);

        return ResponseEntity.created(location).body(ApplicationResponse.<CustomerQueryResponse>builder()
                .success(true)
                .message("Query raised successfully")
                .data(response)
                .build());
    }

    @PreAuthorize("hasRole('LOCAL_TECHNICIAN')")
    @PatchMapping(ApiPaths.Customer.CUSTOMER_QUERY + ApiPaths.Customer.CUSTOMER_QUERY_ID + ApiPaths.Customer.CUSTOMER_QUERY_RESOLVE)
    public ResponseEntity<ApplicationResponse<CustomerQueryResponse>> resolveQuery(@PathVariable(name = "queries-id") Long queryId,
                                                                                   @Valid @RequestBody TenantRequest request){
        CustomerQueryResponse response = customerQueryService.resolveQuery(queryId, request);
        return ResponseEntity.ok(ApplicationResponse.<CustomerQueryResponse>builder()
                .success(true)
                .message("Query resolved successfully")
                .data(response)
                .build());
    }
}
