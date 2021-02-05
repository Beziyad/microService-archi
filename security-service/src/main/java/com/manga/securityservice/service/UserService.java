package com.manga.securityservice.service;

import com.manga.securityservice.model.UserCred;
import com.manga.securityservice.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class UserService implements UserDetailsService {
    @Autowired
    private UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        UserCred userCred = userRepository.findUserByUsername(username);
        if (userCred == null) {
            throw new UsernameNotFoundException("User not found");
        }
        User.UserBuilder builder;
        builder = User.withUsername(userCred.getUsername());
        builder.password(userCred.getPassword());
        return builder.build();
    }
}
