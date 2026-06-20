package com.coditas.powerbridge.dto.response;

import com.coditas.powerbridge.enums.MeterType;
import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class MeterServiceProviderResponse {
    private MeterType meterType;
    private ServiceProviderResponse serviceProvider;
    private double ratePerUnit;
}
