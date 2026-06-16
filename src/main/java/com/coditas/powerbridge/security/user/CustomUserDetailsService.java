package com.coditas.powerbridge.security.user;

import com.coditas.powerbridge.constants.ExceptionMessage;
import com.coditas.powerbridge.repository.EmployeeRepository;
import com.coditas.powerbridge.repository.UserRepository;
import com.coditas.powerbridge.tenant.TenantContext;
import lombok.RequiredArgsConstructor;
import org.jspecify.annotations.NullMarked;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CustomUserDetailsService implements UserDetailsService {

    private final UserRepository userRepository;
    private final EmployeeRepository employeeRepository;

    @Override
    @NullMarked
    public UserDetails loadUserByUsername(String identity) throws UsernameNotFoundException {
        String currentTenant = TenantContext.getCurrentTenant();
        UserDetails user = null;
        if(currentTenant==null || currentTenant.equals("public")){
            user = userRepository.findByUsername(identity);
            if(user == null)
                user = userRepository.findByEmail(identity);
        }else {
            user = employeeRepository.findByEmail(identity);
        }

        if(user == null){
            throw new UsernameNotFoundException(ExceptionMessage.USER_NOT_FOUND);
        }
        return user;
    }
}
