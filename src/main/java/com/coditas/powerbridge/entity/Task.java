package com.coditas.powerbridge.entity;

import com.coditas.powerbridge.enums.Status;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.JdbcTypeCode;
import org.hibernate.type.SqlTypes;

import java.time.Instant;

@Entity
@Table(name = "assigned_tasks")
@Getter @Setter
public class Task {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String task;

    @ManyToOne
    @JoinColumn(name = "sales_team_member_id")
    private User salesTeamMember;

    @ManyToOne
    @JoinColumn(name = "management_team_member_id")
    private User managementTeamMember;

    @Enumerated(EnumType.STRING)
    @JdbcTypeCode(SqlTypes.NAMED_ENUM)
    @Column(columnDefinition = "status")
    private Status status;

    @Column(name = "assigned_at")
    private Instant assignedAt;
}
