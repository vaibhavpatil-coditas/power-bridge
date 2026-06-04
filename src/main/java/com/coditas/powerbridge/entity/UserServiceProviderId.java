package com.coditas.powerbridge.entity;

import jakarta.persistence.Embeddable;
import lombok.*;

import java.io.Serializable;

@Embeddable
@Getter @Setter
@EqualsAndHashCode
@AllArgsConstructor
@NoArgsConstructor
public class UserServiceProviderId implements Serializable {
    private Long userId;
    private Long serviceProviderId;
}
