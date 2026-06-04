package com.coditas.powerbridge.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.Instant;

@Entity
@Table(name = "bills")
@Getter @Setter
public class Bills {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Double amount;

    @ManyToOne
    @JoinColumn(name = "service_provider_id")
    private ServiceProvider serviceProvider;

    @Column(name = "bill_date")
    private Instant billDate;

    @Column(name = "due_date")
    private Instant dueDate;
}
