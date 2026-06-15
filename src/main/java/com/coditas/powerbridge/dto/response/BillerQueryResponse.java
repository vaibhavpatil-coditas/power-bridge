package com.coditas.powerbridge.dto.response;

import com.coditas.powerbridge.enums.BillerQueryStatus;
import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class BillerQueryResponse {
    private Long id;
    private UserResponse biller;
    private String query;
    private BillerQueryStatus status;
}
