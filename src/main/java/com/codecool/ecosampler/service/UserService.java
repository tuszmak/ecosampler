package com.codecool.ecosampler.service;

import com.codecool.ecosampler.controller.dto.user.NewUser;
import com.codecool.ecosampler.controller.dto.user.UserDTO;
import com.codecool.ecosampler.controller.dto.user.UserForSelectDTO;
import com.codecool.ecosampler.domain.Role;
import com.codecool.ecosampler.domain.User;
import com.codecool.ecosampler.exeption.BadRequestException;
import com.codecool.ecosampler.exeption.NotFoundException;
import com.codecool.ecosampler.repository.UserRepository;
import com.codecool.ecosampler.utilities.UserMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Service
public class UserService {
    private final PasswordEncoder passwordEncoder;
    private final UserRepository userRepository;

    public UserDTO getUserDTOByPublicId(UUID publicId) {
        return UserMapper.mapToDTO(getUserByPublicId(publicId));
    }

    public UserDTO registerUser(NewUser newUser) {
        if (userRepository.existsUserByEmail(newUser.email()))
            throw new BadRequestException("User already exists with email: " + newUser.email());
        final User user = userRepository
                .save(UserMapper.mapToNewUser(newUser, passwordEncoder.encode(newUser.password())));
        return UserMapper.mapToDTO(user);
    }

    public void deleteUserByPublicId(UUID publicId) {
        User user = getUserByPublicId(publicId);
        userRepository.deleteById(user.getId());
    }

    public List<UserForSelectDTO> getUsersForSelectDTOByRole(Role role) {
        return getAllUserByRole(role).stream()
                .map(UserMapper::toUserForSelectorDTO)
                .collect(Collectors.toList());
    }

    protected User getUserByPublicId(UUID publicId) {
        return userRepository
                .findByPublicId(publicId)
                .orElseThrow(() -> new NotFoundException("No user by ID:" + publicId));
    }

    protected List<User> getAllUserByRole(Role role) {
        return userRepository.findAllByRole(role);
    }

    protected List<User> getUsersByPublicId(List<UUID> usersPublicId) {
        return userRepository.findAllByPublicIdIn(usersPublicId);
    }

    protected User getUserByEmail(String email) {
        return userRepository.findByEmail(email).orElseThrow(() -> new NotFoundException("No user fund on email"));
    }
}
