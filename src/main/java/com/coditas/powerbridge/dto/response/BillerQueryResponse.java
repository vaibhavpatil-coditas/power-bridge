package com.coditas.powerbridge.dto.response;

import com.coditas.powerbridge.enums.BillerQueryStatus;
import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class BillerQueryResponse {
    private Long id;
    private Long billerId;
    private String query;
    private BillerQueryStatus status;
}
