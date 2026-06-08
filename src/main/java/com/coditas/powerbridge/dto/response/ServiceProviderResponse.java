package com.coditas.powerbridge.dto.response;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Builder
@Getter @Setter
public class ServiceProviderResponse {
    private UserResponse user;
}
