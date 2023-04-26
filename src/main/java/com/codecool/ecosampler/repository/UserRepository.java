package com.codecool.ecosampler.repository;

import com.codecool.ecosampler.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {
    boolean existsUserByEmail(String email);

}
