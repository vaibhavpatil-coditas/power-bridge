package com.coditas.powerbridge.repository;

import com.coditas.powerbridge.entity.ServiceProvider;
import jakarta.validation.constraints.NotBlank;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ServiceProviderRepository extends JpaRepository<ServiceProvider, Long> {
    boolean existsByName(@NotBlank String name);
}
