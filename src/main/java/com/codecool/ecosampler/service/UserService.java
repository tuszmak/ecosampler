package com.codecool.ecosampler.service;

import com.codecool.ecosampler.controller.dto.user.NewUser;
import com.codecool.ecosampler.controller.dto.user.UserDTO;
import com.codecool.ecosampler.exeption.BadRequestException;
import com.codecool.ecosampler.exeption.NotFoundException;
import com.codecool.ecosampler.repository.UserRepository;
import com.codecool.ecosampler.utilities.Mapper;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@AllArgsConstructor
@Service
public class UserService {
    private UserRepository userRepository;

    public UserDTO getUserByID(Long id) {
        return Mapper.mapToDTO(userRepository
                .findById(id)
                .orElseThrow(() -> new NotFoundException("No user by ID:" + id)));
    }

    public Long registerUser(NewUser newUser) {
        if (userRepository.existsUserByEmail(newUser.email()))
            throw new BadRequestException("User already exists with email: " + newUser.email());
        return userRepository
                .save(Mapper.mapToUser(newUser))
                .getId();
    }

    public void deleteUserByID(Long id) {
        if (userRepository.existsById(id))
            userRepository.deleteById(id);
        else
            throw new NotFoundException("No User with id: " + id);
    }
}
