package com.coditas.powerbridge.dto.response;

import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class AreaResponse {
    private Long id;
    private String name;
    private CityResponse city;
    private UserResponse technician;
    private UserResponse biller;
}
