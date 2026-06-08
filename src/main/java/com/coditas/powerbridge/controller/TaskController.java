package com.coditas.powerbridge.controller;

import com.coditas.powerbridge.constants.ApiPaths;
import com.coditas.powerbridge.dto.request.TaskRequest;
import com.coditas.powerbridge.dto.response.ApplicationResponse;
import com.coditas.powerbridge.dto.response.TaskResponse;
import com.coditas.powerbridge.service.TaskService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.net.URI;

@RestController
@RequiredArgsConstructor
public class TaskController {

    private final TaskService taskService;

    @PreAuthorize("hasRole('MANAGEMENT_TEAM_MEMBER')")
    @PostMapping(ApiPaths.ManagementTeam.BASE + ApiPaths.SalesTeam.ASSIGN_TASK)
    public ResponseEntity<ApplicationResponse<TaskResponse>> assignTaskToSalesTeamMember(@Valid @RequestBody TaskRequest taskRequest){
        TaskResponse response = taskService.assignTaskToSalesTeamMember(taskRequest);
        URI location = URI.create(ApiPaths.SalesTeam.BASE
                + "/" + response.getSalesTeamMember().getId()
                + ApiPaths.SalesTeam.TASKS);

        return ResponseEntity.created(location)
                .body(ApplicationResponse.<TaskResponse>builder()
                        .success(true)
                        .message("Task has been successfully assigned")
                        .data(response)
                        .build());
    }
}
