package com.coditas.powerbridge.dto.request;

import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class UserServiceProviderRequest {
    private Long userId;
    private Long serviceProviderId;
}
