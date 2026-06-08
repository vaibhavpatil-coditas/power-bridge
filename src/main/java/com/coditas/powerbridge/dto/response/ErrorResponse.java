package com.coditas.powerbridge.dto.response;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Builder
@Getter @Setter
public class ErrorResponse {
    private String message;
    private LocalDateTime timestamp;
    private Integer status;
}
