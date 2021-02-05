package com.manga.securityservice.repository;

import com.manga.securityservice.model.UserCred;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<UserCred, Long> {
    UserCred findUserByUsername(String username);
}
