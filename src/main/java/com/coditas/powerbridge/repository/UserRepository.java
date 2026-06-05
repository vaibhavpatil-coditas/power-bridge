package com.coditas.powerbridge.repository;

import com.coditas.powerbridge.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {
}
