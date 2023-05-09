package com.codecool.ecosampler.service;

import com.codecool.ecosampler.controller.dto.user.NewUser;
import com.codecool.ecosampler.controller.dto.user.UserDTO;
import com.codecool.ecosampler.domain.User;
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

    public UserDTO getUserDTOByPublicId(UUID publicId) {
        return Mapper.mapToDTO(getUserByPublicId(publicId));
    }

    public UserDTO registerUser(NewUser newUser) {
        if (userRepository.existsUserByEmail(newUser.email()))
            throw new BadRequestException("User already exists with email: " + newUser.email());
        final User user = userRepository
                .save(Mapper.mapToNewUser(newUser));
        return Mapper.mapToDTO(user);
    }

    public void deleteUserByPublicId(UUID publicId) {
        User user = getUserByPublicId(publicId);
        userRepository.deleteById(user.getId());
    }

    public User getUserByPublicId(UUID publicId) {
        return userRepository
                .findByPublicId(publicId)
                .orElseThrow(() -> new NotFoundException("No user by ID:" + publicId));
    }
}
