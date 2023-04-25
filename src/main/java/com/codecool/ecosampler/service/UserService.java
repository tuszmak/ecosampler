package com.codecool.ecosampler.service;

import com.codecool.ecosampler.controller.dto.NewUser;
import com.codecool.ecosampler.controller.dto.UserDTO;
import com.codecool.ecosampler.domain.User;
import com.codecool.ecosampler.repository.UserRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.NoSuchElementException;

@AllArgsConstructor
@Service
public class UserService {
    private UserRepository userRepository;

    public UserDTO getUserByID(Long id) {
        User user = userRepository
                .findById(id)
                .orElseThrow(() -> new NoSuchElementException("No user by ID:" + id));
        return new UserDTO(user.getName()); // TODO: convert User to UserDTO
    }

    public Long registerUser(NewUser newUser) {
        if (userRepository.existsUserByEmail(newUser.email()))
            throw new RuntimeException("User already exists with email: " + newUser.email());
        return userRepository
                .save(new User(newUser.email(), newUser.name(), newUser.password()))
                .getId();
    }

    public void deleteUserByID(Long id) {
        if (userRepository.existsById(id)) {
            userRepository.deleteById(id);
        } else
            throw new NoSuchElementException("No User with id: " + id);
    }
}
