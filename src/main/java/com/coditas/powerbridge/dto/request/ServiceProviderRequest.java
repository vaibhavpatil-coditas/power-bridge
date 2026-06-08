package com.coditas.powerbridge.dto.request;

import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class ServiceProviderRequest {
    @NotNull
    private UserRequest user;
}
