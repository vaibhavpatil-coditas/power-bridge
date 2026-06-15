package com.coditas.powerbridge.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.Instant;

@Entity
@Table(name = "electricity_bills", schema = "public")
@Getter @Setter
public class ElectricityBill {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "customer_id")
    private Customer customer;

    private double amount;

    @Column(name = "generated_at")
    private Instant generatedAt;

    @Column(name = "due_date")
    private Instant dueDate;
}
