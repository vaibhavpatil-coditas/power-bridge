package com.coditas.powerbridge.repository;

import com.coditas.powerbridge.entity.City;
import jakarta.validation.constraints.NotBlank;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CityRepository extends JpaRepository<City, Long> {
    boolean existsByName(@NotBlank String name);
}
