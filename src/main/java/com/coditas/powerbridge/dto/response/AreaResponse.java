package com.coditas.powerbridge.dto.response;

import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class AreaResponse {
    private Long id;
    private String name;
    private Long cityId;
    private Long technicianId;
    private Long billerId;
}
