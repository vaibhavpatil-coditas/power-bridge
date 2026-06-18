package com.coditas.powerbridge.entity;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "bpo_states")
@Builder
@Getter @Setter
@NoArgsConstructor
@AllArgsConstructor
public class BPOState {
    @EmbeddedId
    private BPOStateId id;

    @ManyToOne
    @MapsId("employeeId")
    @JoinColumn(name = "bpo_id")
    private Employee bpo;

    @OneToOne
    @MapsId("stateId")
    private State state;
}
