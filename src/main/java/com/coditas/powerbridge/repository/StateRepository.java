package com.coditas.powerbridge.repository;

import com.coditas.powerbridge.entity.State;
import jakarta.validation.constraints.NotBlank;
import org.springframework.data.jpa.repository.JpaRepository;

public interface StateRepository extends JpaRepository<State, Long> {
    boolean existsByName(@NotBlank String name);
}
