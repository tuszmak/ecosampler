package com.codecool.ecosampler.service;

import com.codecool.ecosampler.controller.dto.NewUser;
import com.codecool.ecosampler.controller.dto.UserDTO;
import com.codecool.ecosampler.repository.UserRepository;
import com.codecool.ecosampler.utilities.Mapper;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.NoSuchElementException;

@AllArgsConstructor
@Service
public class UserService {
    private UserRepository userRepository;

    public UserDTO getUserByID(Long id) {
        return Mapper.mapToDTO(userRepository
                .findById(id)
                .orElseThrow(() -> new NoSuchElementException("No user by ID:" + id)));
    }

    public Long registerUser(NewUser newUser) {
        if (userRepository.existsUserByEmail(newUser.email()))
            throw new RuntimeException("User already exists with email: " + newUser.email());
        return userRepository
                .save(Mapper.mapToUser(newUser))
                .getId();
    }

    public void deleteUserByID(Long id) {
        if (userRepository.existsById(id))
            userRepository.deleteById(id);
         else
            throw new NoSuchElementException("No User with id: " + id);
    }
}
