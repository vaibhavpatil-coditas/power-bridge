package com.coditas.powerbridge.repository;

import com.coditas.powerbridge.entity.District;
import jakarta.validation.constraints.NotBlank;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DistrictRepository extends JpaRepository<District, Long> {

    boolean existsByName(@NotBlank String name);
}
