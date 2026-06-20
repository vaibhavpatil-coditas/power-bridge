package com.coditas.powerbridge.repository;

import com.coditas.powerbridge.entity.Area;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface AreaRepository extends JpaRepository<Area, Long> {
    Optional<List<Area>> findByCity(com.coditas.powerbridge.entity.City city);
}
