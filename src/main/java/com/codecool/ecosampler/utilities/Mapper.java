package com.codecool.ecosampler.utilities;

import com.codecool.ecosampler.controller.dto.login.LoginCredentials;
import com.codecool.ecosampler.controller.dto.user.NewUser;
import com.codecool.ecosampler.controller.dto.user.UserDTO;
import com.codecool.ecosampler.controller.dto.user.UserForSelectDTO;
import com.codecool.ecosampler.domain.User;

import java.util.UUID;

public class Mapper {

    public static UserDTO mapToDTO(User user) {
        return new UserDTO(user.getPublicId(), user.getName(), user.getEmail(), user.getRole());
    }

    public static User mapToNewUser(NewUser newUser) {
        return new User(UUID.randomUUID(), newUser.name(), newUser.email(), newUser.password());
    }

    public static LoginCredentials mapToResponseUser(User user) {
        return new LoginCredentials(user.getPublicId(), user.getRole());
    }

    public static UserForSelectDTO toUserForSelectorDTO(User user) {
        return new UserForSelectDTO(user.getPublicId(), user.getName(), user.getRole());
    }
}
