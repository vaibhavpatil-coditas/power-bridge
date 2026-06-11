package com.coditas.powerbridge.repository;

import com.coditas.powerbridge.entity.Customer;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CustomerRepository extends JpaRepository<Customer, Long> {
}
