package com.coditas.powerbridge.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "employees")
@Getter @Setter
public class Employee {
    @Id
    @Column(name = "user_id")
    private long id;

    @MapsId
    @OneToOne
    @JoinColumn(name = "user_id")
    private User user;
}
