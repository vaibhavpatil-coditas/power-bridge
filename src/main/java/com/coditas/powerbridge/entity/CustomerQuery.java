package com.coditas.powerbridge.entity;

import com.coditas.powerbridge.enums.QueryStatus;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.JdbcTypeCode;
import org.hibernate.type.SqlTypes;

import java.time.Instant;

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

    @Enumerated(EnumType.STRING)
    @JdbcTypeCode(SqlTypes.NAMED_ENUM)
    @Column(columnDefinition = "query_status")
    private QueryStatus status;

    @Column(name = "query_date")
    private Instant queryDate;

    @Column(name = "resolved_date")
    private Instant resolvedDate;

    @Column(name = "first_escalated_on")
    private Instant firstEscalatedOn;

    @Column(name = "second_escalated_on")
    private Instant secondEscalatedOn;
}
