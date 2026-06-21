package com.coditas.powerbridge.repository;

import com.coditas.powerbridge.entity.Customer;
import com.coditas.powerbridge.entity.ElectricityBill;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface ElectricityBillRepository extends JpaRepository<ElectricityBill, Long> {
    List<ElectricityBill> findByCustomer(Customer customer);
}
