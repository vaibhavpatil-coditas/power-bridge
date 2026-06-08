package com.coditas.powerbridge.dto.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter @Setter
@NoArgsConstructor
public class TaskRequest {
    @NotBlank
    private String task;
    @NotNull
    private Long salesTeamMemberId;
}
