package com.coditas.powerbridge.dto.response;

import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class CustomerResponse {
    private Long id;
    private Long userId;
    private String address;
    private String mobileNumber;
    private Long areaId;
}