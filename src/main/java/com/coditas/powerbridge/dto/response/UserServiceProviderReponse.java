package com.coditas.powerbridge.dto.response;

import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class UserServiceProviderReponse {
    private UserResponse user;
    private ServiceProviderResponse serviceProvider;
}
