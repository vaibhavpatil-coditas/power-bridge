package com.coditas.powerbridge.common;

import com.coditas.powerbridge.entity.User;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class ServiceUtil {
    private final PasswordEncoder passwordEncoder;

    public String encodePassword(User user) {
        return passwordEncoder.encode(user.getPassword());
    }
}
