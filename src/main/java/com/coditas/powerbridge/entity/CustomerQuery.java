package com.coditas.powerbridge.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "customer_queries")
@Getter @Setter
public class CustomerQuery {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String query;

    @ManyToOne
    @JoinColumn(name = "customer_id")
    private Customer customer;
}
