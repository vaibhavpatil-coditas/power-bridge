package com.coditas.powerbridge.repository;

import com.coditas.powerbridge.entity.City;
import com.coditas.powerbridge.entity.District;
import jakarta.validation.constraints.NotBlank;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface CityRepository extends JpaRepository<City, Long> {
    boolean existsByName(@NotBlank String name);

    Optional<List<City>> findByDistrict(District district);
}
