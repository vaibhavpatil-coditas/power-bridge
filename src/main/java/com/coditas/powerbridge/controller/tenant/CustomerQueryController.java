package com.coditas.powerbridge.controller.tenant;

import com.coditas.powerbridge.constants.ApiPaths;
import com.coditas.powerbridge.dto.request.CustomerQueryRequest;
import com.coditas.powerbridge.dto.response.ApplicationResponse;
import com.coditas.powerbridge.dto.response.CustomerQueryResponse;
import com.coditas.powerbridge.service.CustomerQueryService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;

@RestController
@RequiredArgsConstructor
@RequestMapping(ApiPaths.BASE_PATH)
public class CustomerQueryController {

    private final CustomerQueryService customerQueryService;

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
}
