package com.coditas.powerbridge.service;

import com.coditas.powerbridge.dto.request.TaskRequest;
import com.coditas.powerbridge.dto.response.TaskResponse;
import jakarta.validation.Valid;

public interface TaskService {
    TaskResponse assignTaskToSalesTeamMember(@Valid TaskRequest taskRequest);
}
