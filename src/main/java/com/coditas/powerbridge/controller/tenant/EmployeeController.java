package com.coditas.powerbridge.controller.tenant;

import com.coditas.powerbridge.constants.ApiPaths;
import com.coditas.powerbridge.dto.request.EmployeeRequest;
import com.coditas.powerbridge.dto.response.ApplicationResponse;
import com.coditas.powerbridge.dto.response.EmployeeResponse;
import com.coditas.powerbridge.service.EmployeeService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.net.URI;

@RestController
@RequestMapping(ApiPaths.BASE_PATH)
@RequiredArgsConstructor
public class EmployeeController {

    private final EmployeeService employeeService;

    @PreAuthorize("hasRole('SERVICE_PROVIDER')")
    @PostMapping(ApiPaths.Employee.SALES_TEAM)
    public ResponseEntity<ApplicationResponse<EmployeeResponse>> onboardSalesTeamMember(@Valid @RequestBody EmployeeRequest request){
        EmployeeResponse response = employeeService.onboardSalesTeamMember(request);

        URI location = URI.create(ApiPaths.BASE_PATH + ApiPaths.Employee.SALES_TEAM + "/" + response.getId());

        return ResponseEntity.created(location).body(ApplicationResponse.<EmployeeResponse>builder()
                .success(true)
                .message("Sales team member successfully onboarded")
                .data(response)
                .build());
    }
}
