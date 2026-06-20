package com.coditas.powerbridge.repository;

import com.coditas.powerbridge.entity.District;
import com.coditas.powerbridge.entity.State;
import jakarta.validation.constraints.NotBlank;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface DistrictRepository extends JpaRepository<District, Long> {

    boolean existsByName(@NotBlank String name);

    Optional<List<District>> findByState(State state);
}
