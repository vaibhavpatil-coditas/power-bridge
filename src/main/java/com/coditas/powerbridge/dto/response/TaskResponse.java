package com.coditas.powerbridge.dto.response;

import com.coditas.powerbridge.enums.Status;
import lombok.Getter;
import lombok.Setter;

import java.time.Instant;

@Getter @Setter
public class TaskResponse {
    private Long id;
    private String task;
    private UserResponse assignedTo;
    private UserResponse assignedBy;
    private Status status;
    private Instant assignedAt;
}
