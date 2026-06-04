package com.coditas.powerbridge.entity;

import jakarta.persistence.Embeddable;
import lombok.*;

import java.io.Serializable;

@Embeddable
@Getter @Setter
@EqualsAndHashCode
@NoArgsConstructor
@AllArgsConstructor
public class ServiceProviderStateId implements Serializable {
    private Long serviceProviderId;
    private Long stateId;
}
