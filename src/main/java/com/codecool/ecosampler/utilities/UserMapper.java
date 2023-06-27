package com.codecool.ecosampler.utilities;

import com.codecool.ecosampler.controller.dto.login.LoginCredentials;
import com.codecool.ecosampler.controller.dto.user.NewUser;
import com.codecool.ecosampler.controller.dto.user.UserDTO;
import com.codecool.ecosampler.controller.dto.user.UserForSelectDTO;
import com.codecool.ecosampler.domain.AuthUser;
import com.codecool.ecosampler.domain.User;

import java.util.UUID;

public class UserMapper {

    public static UserDTO mapToDTO(User user) {
        return new UserDTO(user.getPublicId(), user.getName(), user.getEmail(), user.getRole());
    }

    public static User mapToUser(NewUser newUser, String encryptedPassword) {
        return new User(UUID.randomUUID(), newUser.name(), newUser.email(), newUser.role(), encryptedPassword);
    }

    public static LoginCredentials toLoginCredential(User user, String token) {
        return new LoginCredentials(user.getPublicId(), user.getRole(), user.getName(), token);
    }

    public static UserForSelectDTO toUserForSelectorDTO(User user) {
        return new UserForSelectDTO(user.getPublicId(), user.getName(), user.getRole());
    }

    public static AuthUser toAuthUser(User user) {
        return new AuthUser(user.getEmail(), user.getPassword(), user.getRole());
    }
}
