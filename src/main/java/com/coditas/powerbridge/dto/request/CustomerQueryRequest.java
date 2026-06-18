package com.coditas.powerbridge.dto.request;

import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class CustomerQueryRequest {
    private Long customerId;
    private String query;
}
