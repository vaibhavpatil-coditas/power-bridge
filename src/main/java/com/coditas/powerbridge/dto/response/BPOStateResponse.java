package com.coditas.powerbridge.dto.response;

import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class BPOStateResponse {
    private EmployeeResponse employee;
    private StateResponse state;
}
