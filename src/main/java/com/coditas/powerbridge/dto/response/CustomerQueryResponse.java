package com.coditas.powerbridge.dto.response;

import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class CustomerQueryResponse {
    private Long id;
    private String query;
    private CustomerResponse customer;
}
