package com.coditas.powerbridge.controller.tenant;

import com.coditas.powerbridge.constants.ApiPaths;
import com.coditas.powerbridge.dto.request.BPOStateRequest;
import com.coditas.powerbridge.dto.request.EmployeeRequest;
import com.coditas.powerbridge.dto.response.ApplicationResponse;
import com.coditas.powerbridge.dto.response.BPOStateResponse;
import com.coditas.powerbridge.dto.response.EmployeeResponse;
import com.coditas.powerbridge.service.EmployeeService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

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

    @PreAuthorize("hasRole('SALES_TEAM_MEMBER')")
    @PostMapping(ApiPaths.Employee.OPERATIONS_HEAD)
    public ResponseEntity<ApplicationResponse<EmployeeResponse>> onboardOperationsHead(@Valid @RequestBody EmployeeRequest request){
        EmployeeResponse response = employeeService.onboardOperationsHead(request);

        URI location = URI.create(ApiPaths.BASE_PATH + ApiPaths.Employee.OPERATIONS_HEAD + "/" + response.getId());

        return ResponseEntity.created(location).body(ApplicationResponse.<EmployeeResponse>builder()
                .success(true)
                .message("Operations head successfully onboarded")
                .data(response)
                .build());
    }

    @PreAuthorize("hasRole('OPERATIONS_HEAD')")
    @PostMapping(ApiPaths.Employee.BPO)
    public ResponseEntity<ApplicationResponse<EmployeeResponse>> onboardBPO(@Valid @RequestBody EmployeeRequest request){
        EmployeeResponse response = employeeService.onboardBPO(request);

        URI location = URI.create(ApiPaths.BASE_PATH + ApiPaths.Employee.BPO + "/" + response.getId());

        return ResponseEntity.created(location).body(ApplicationResponse.<EmployeeResponse>builder()
                .success(true)
                .message("BPO successfully onboarded")
                .data(response)
                .build());
    }

    @PreAuthorize("hasRole('OPERATIONS_HEAD')")
    @PostMapping(ApiPaths.Employee.MANAGER1)
    public ResponseEntity<ApplicationResponse<EmployeeResponse>> onboardManager1(@Valid @RequestBody EmployeeRequest request){
        EmployeeResponse response = employeeService.onboardManager1(request);

        URI location = URI.create(ApiPaths.BASE_PATH + ApiPaths.Employee.MANAGER1 + "/" + response.getId());

        return ResponseEntity.created(location).body(ApplicationResponse.<EmployeeResponse>builder()
                .success(true)
                .message("Manager-1 successfully onboarded")
                .data(response)
                .build());
    }

    @PreAuthorize("hasRole('OPERATIONS_HEAD')")
    @PostMapping(ApiPaths.Employee.MANAGER2)
    public ResponseEntity<ApplicationResponse<EmployeeResponse>> onboardManager2(@Valid @RequestBody EmployeeRequest request){
        EmployeeResponse response = employeeService.onboardManager2(request);

        URI location = URI.create(ApiPaths.BASE_PATH + ApiPaths.Employee.MANAGER2 + "/" + response.getId());

        return ResponseEntity.created(location).body(ApplicationResponse.<EmployeeResponse>builder()
                .success(true)
                .message("Manager-2 successfully onboarded")
                .data(response)
                .build());
    }

    @PreAuthorize("hasRole('OPERATIONS_HEAD')")
    @PostMapping(ApiPaths.Employee.BPO_ID + ApiPaths.State.STATES)
    public ResponseEntity<ApplicationResponse<BPOStateResponse>> assignStateToBPO(@PathVariable(name = "bpo-id") Long bpoId,
                                                                                  @Valid @RequestBody BPOStateRequest request){
        BPOStateResponse response = employeeService.assignStateToBPO(bpoId, request);

        URI location = URI.create(ApiPaths.BASE_PATH + ApiPaths.Employee.BPO + bpoId + ApiPaths.State.STATES);

        return ResponseEntity.created(location).body(ApplicationResponse.<BPOStateResponse>builder()
                .success(true)
                .message("State assigned to bpo")
                .data(response)
                .build());
    }
}