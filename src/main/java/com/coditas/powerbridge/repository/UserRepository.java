package com.coditas.powerbridge.repository;

import com.coditas.powerbridge.entity.User;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {
    User findByUsername(String username);

    User findByEmail(String identity);

    List<User> findAllByUsernameOrEmail(@NotBlank String username, @Email String email);
}
