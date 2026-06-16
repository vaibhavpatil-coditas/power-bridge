package com.coditas.powerbridge.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "service_provider_state")
@Getter @Setter
public class ServiceProviderState {
    @EmbeddedId
    private ServiceProviderStateId id;

    @MapsId("serviceProviderId")
    @ManyToOne
    @JoinColumn(name = "service_provider_id")
    private ServiceProvider serviceProvider;

    @MapsId("stateId")
    @ManyToOne
    @JoinColumn(name = "state_id")
    private State state;
}
