package com.coditas.powerbridge.entity;

import com.coditas.powerbridge.enums.BillerQueryStatus;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.JdbcTypeCode;
import org.hibernate.type.SqlTypes;

@Entity
@Table(name = "biller_queries", schema = "public")
@Getter @Setter
public class BillerQuery {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "biller_id")
    private User biller;

    private String query;

    @Enumerated(EnumType.STRING)
    @JdbcTypeCode(SqlTypes.NAMED_ENUM)
    @Column(columnDefinition = "biller_query_status")
    private BillerQueryStatus status;
}
