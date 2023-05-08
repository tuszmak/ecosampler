package com.codecool.ecosampler.service;

import com.codecool.ecosampler.controller.dto.user.NewUser;
import com.codecool.ecosampler.controller.dto.user.UserDTO;
import com.codecool.ecosampler.exeption.BadRequestException;
import com.codecool.ecosampler.exeption.NotFoundException;
import com.codecool.ecosampler.repository.UserRepository;
import com.codecool.ecosampler.utilities.Mapper;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.UUID;

@AllArgsConstructor
@Service
public class UserService {
    private UserRepository userRepository;

    public UserDTO getUserByPublicId(UUID publicId) {
        return Mapper.mapToDTO(userRepository
                .findByPublicId(publicId)
                .orElseThrow(() -> new NotFoundException("No user by ID:" + publicId)));
    }

    public UUID registerUser(NewUser newUser) {
        if (userRepository.existsUserByEmail(newUser.email()))
            throw new BadRequestException("User already exists with email: " + newUser.email());
        return userRepository
                .save(Mapper.mapToUser(newUser))
                .getPublicId();
    }

    public void deleteUserByPublicId(UUID publicId) {
        userRepository.findByPublicId(publicId)
                .ifPresentOrElse(
                        user -> userRepository.deleteById(user.getId()),
                        () -> {
                            throw new NotFoundException("No User with id: " + publicId);
                        }
                );
    }
}
