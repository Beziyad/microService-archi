package com.manga.securityservice.services;

import com.manga.securityservice.model.UserInfo;
import com.manga.securityservice.repository.UserRepository;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import javax.inject.Inject;

@Service
public class CustomUserDetailsService implements UserDetailsService {

    @Inject
    UserRepository userRepository;

    public UserDetails loadUserByUsername(String userName) {

        UserInfo user = userRepository.findByUsername(userName);

        if (user == null) {
            throw new UsernameNotFoundException(
                    String.format("Username not found for domain, "
                            + "username=%s", userName));
        }
        User.UserBuilder builder;

        builder = User.withUsername(user.getUsername());
        builder.password(user.getPasswordhash());
        builder.roles(user.getRole());
        return builder.build();
    }
}
