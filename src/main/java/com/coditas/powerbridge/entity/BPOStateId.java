package com.coditas.powerbridge.entity;

import jakarta.persistence.Embeddable;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Embeddable
@NoArgsConstructor
@AllArgsConstructor
public class BPOStateId implements Serializable {
    private Long employeeId;
    private Long stateId;
}