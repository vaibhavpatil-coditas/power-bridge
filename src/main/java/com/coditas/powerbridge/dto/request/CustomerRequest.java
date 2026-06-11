package com.coditas.powerbridge.dto.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class CustomerRequest {
    @NotNull
    private UserRequest user;
    @NotBlank
    private String address;
    @NotBlank
    private String mobileNumber;
    @NotNull
    private CustomerAreaRequest area;
}