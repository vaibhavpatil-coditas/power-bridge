package com.coditas.powerbridge.controller;

import com.coditas.powerbridge.constants.ApiPaths;
import com.coditas.powerbridge.dto.request.CustomerRequest;
import com.coditas.powerbridge.dto.request.UserServiceProviderRequest;
import com.coditas.powerbridge.dto.response.ApplicationResponse;
import com.coditas.powerbridge.dto.response.CustomerResponse;
import com.coditas.powerbridge.dto.response.UserServiceProviderReponse;
import com.coditas.powerbridge.service.CustomerService;
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
public class CustomerController {

    private final CustomerService customerService;

    @PostMapping(ApiPaths.Customer.CUSTOMERS)
    @PreAuthorize("hasRole('CRM')")
    public ResponseEntity<ApplicationResponse<CustomerResponse>> create(@Valid @RequestBody CustomerRequest request){
        CustomerResponse response = customerService.create(request);

        URI location = URI.create(ApiPaths.Customer.CUSTOMERS+
                "/"+response.getId());

        return ResponseEntity.created(location).body(ApplicationResponse.<CustomerResponse>builder()
                .success(true)
                .message("Customer created successfully")
                .data(response)
                .build());
    }

    @GetMapping(ApiPaths.Customer.CUSTOMERS)
    @PreAuthorize("hasRole('CRM')")
    public ResponseEntity<ApplicationResponse<List<CustomerResponse>>> getAll(){
        List<CustomerResponse> response = customerService.getAll();
        return ResponseEntity.ok().body(ApplicationResponse.<List<CustomerResponse>>builder()
                .success(true)
                .message("Customers fetched successfully")
                .data(response)
                .build());
    }

    @GetMapping(ApiPaths.Customer.CUSTOMERS + ApiPaths.Customer.ID)
    @PreAuthorize("hasRole('CRM')")
    public ResponseEntity<ApplicationResponse<CustomerResponse>> getCustomerById(@PathVariable(name = "customer-id") Long customerId){
        CustomerResponse response = customerService.getCustomerById(customerId);
        return ResponseEntity.ok().body(ApplicationResponse.<CustomerResponse>builder()
                .success(true)
                .message("Customers fetched successfully")
                .data(response)
                .build());
    }

    @PreAuthorize("hasRole('CRM')")
    @PostMapping(ApiPaths.Customer.CUSTOMERS + ApiPaths.Customer.ASSIGN_SERVICE_PROVIDER)
    public ResponseEntity<ApplicationResponse<UserServiceProviderReponse>> assignServiceProvider(@Valid @RequestBody UserServiceProviderRequest request){

        UserServiceProviderReponse response = customerService.assignServiceProvider(request);

        return ResponseEntity.ok().body(ApplicationResponse.<UserServiceProviderReponse>builder()
                .success(true)
                .message("Service provider assigned successfully")
                .data(response)
                .build());
    }
}
