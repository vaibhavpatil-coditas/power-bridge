package com.coditas.powerbridge.repository;

import com.coditas.powerbridge.entity.BillerQuery;
import com.coditas.powerbridge.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface BillerQueryRepository extends JpaRepository<BillerQuery, Long> {
    Optional<BillerQuery> findByBiller(User billerId);
}
