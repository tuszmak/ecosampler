package com.codecool.ecosampler.repository;

import com.codecool.ecosampler.domain.Role;
import com.codecool.ecosampler.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;


@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    boolean existsUserByEmail(String email);
    Optional<User> findByPublicId(UUID publicId);
    Optional<User> findByEmail(String email);
    List<User> findAllByRole(Role role);
    List<User> findAllByPublicIdIn(List<UUID> usersPublicId);
}
