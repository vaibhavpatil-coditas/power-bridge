package com.coditas.powerbridge.repository;

import com.coditas.powerbridge.entity.ElectricityBill;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ElectricityBillRepository extends JpaRepository<ElectricityBill, Long> {
}
