package com.coditas.powerbridge.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "user_service_provider")
@Getter @Setter
@NoArgsConstructor
public class UserServiceProvider {

    @EmbeddedId
    private UserServiceProviderId id;

    @ManyToOne
    @JoinColumn(name = "user_id")
    @MapsId("userId")
    private User user;

    @ManyToOne
    @JoinColumn(name = "service_provider_id")
    @MapsId("serviceProviderId")
    private ServiceProvider serviceProvider;
}
