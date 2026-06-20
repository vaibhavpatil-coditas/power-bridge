package com.coditas.powerbridge.dto.request;

import com.coditas.powerbridge.enums.MeterType;
import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class MeterServiceProviderRequest {
    private MeterType meterType;
    private Long serviceProviderId;
    private double ratePerUnit;
}
