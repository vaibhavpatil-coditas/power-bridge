package com.coditas.powerbridge.entity;

import com.coditas.powerbridge.enums.MeterType;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.JdbcTypeCode;
import org.hibernate.type.SqlTypes;

@Entity
@Table(name = "meter_service_providers", schema = "public")
@Getter @Setter
public class MeterServiceProvider {

    @EmbeddedId
    private MeterServiceProviderId id;

    @Enumerated(EnumType.STRING)
    @JdbcTypeCode(SqlTypes.NAMED_ENUM)
    @Column(name = "meter_type", columnDefinition = "meter_type", insertable = false, updatable = false)
    private MeterType meterType;

    @ManyToOne
    @JoinColumn(name = "service_provider_id")
    @MapsId("serviceProviderId")
    private ServiceProvider serviceProvider;

    @Column(name = "rate_per_unit")
    private double ratePerUnit;
}