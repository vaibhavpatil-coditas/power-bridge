package com.coditas.powerbridge.security.user;

import com.coditas.powerbridge.constants.ExceptionMessage;
import com.coditas.powerbridge.entity.User;
import com.coditas.powerbridge.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.jspecify.annotations.NullMarked;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CustomUserDetailsService implements UserDetailsService {

    private final UserRepository userRepository;

    @Override
    @NullMarked
    public User loadUserByUsername(String identity) throws UsernameNotFoundException {
        User user = null;
        user = userRepository.findByUsername(identity);
        if(user == null)
            user = userRepository.findByEmail(identity);

        if(user == null){
            throw new UsernameNotFoundException(ExceptionMessage.USER_NOT_FOUND);
        }
        return user;
    }
}
