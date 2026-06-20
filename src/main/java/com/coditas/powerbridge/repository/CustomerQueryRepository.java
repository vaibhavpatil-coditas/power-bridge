package com.coditas.powerbridge.repository;

import com.coditas.powerbridge.entity.CustomerQuery;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CustomerQueryRepository extends JpaRepository<CustomerQuery, Long> {
}
