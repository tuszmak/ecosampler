package com.codecool.ecosampler.utilities;

import com.codecool.ecosampler.controller.dto.user.NewUser;
import com.codecool.ecosampler.controller.dto.user.UserDTO;
import com.codecool.ecosampler.domain.User;

public class Mapper {

    public static UserDTO mapToDTO(User user) {
        return new UserDTO(user.getName(), user.getEmail(), user.getRole());
    }

    public static User mapToUser(NewUser newUser) {
        return new User(newUser.name(), newUser.email(), newUser.password());
    }
}
