package com.coditas.powerbridge.repository;

import com.coditas.powerbridge.entity.Task;
import jakarta.validation.constraints.NotBlank;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TaskRepository extends JpaRepository<Task, Long> {
    boolean existsByTask(@NotBlank String task);
}
