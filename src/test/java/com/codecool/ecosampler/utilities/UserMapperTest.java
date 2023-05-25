package com.codecool.ecosampler.utilities;

import com.codecool.ecosampler.controller.dto.login.LoginCredentials;
import com.codecool.ecosampler.controller.dto.user.NewUser;
import com.codecool.ecosampler.controller.dto.user.UserDTO;
import com.codecool.ecosampler.controller.dto.user.UserForSelectDTO;
import com.codecool.ecosampler.domain.AuthUser;
import com.codecool.ecosampler.domain.Role;
import com.codecool.ecosampler.domain.User;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertEquals;

class UserMapperTest {
private UUID uuid;
private User user;
@BeforeEach
void setup(){
        uuid = UUID.randomUUID();
        user = new User(uuid, "foo", "bar@foo.cx", Role.SCIENTIST, "test");


}
    @Test
    void should_return_a_userDTO() {
        UserDTO expected = new UserDTO(uuid, "foo", "bar@foo.cx", Role.SCIENTIST);
        UserDTO actual = UserMapper.mapToDTO(user);
        assertEquals(expected, actual);
    }

    @Test
    void should_create_new_user() {
        UUID uuid = UUID.randomUUID();
        NewUser newUser = new NewUser(uuid,
                "foo",
                "bar@foo.cx",
                Role.SCIENTIST,
                "test");
        String securePassword = "IAmSoSecure";
        User expected = user;
        User actual = UserMapper.mapToNewUser(newUser,securePassword);
        assertEquals(expected.getPublicId(),actual.getPublicId());
        assertEquals(expected.getName(),actual.getName());
        assertEquals(expected.getEmail(),actual.getEmail());
        assertEquals(expected.getRole(),actual.getRole());
        assertEquals(expected.getPassword(),actual.getPassword());
    }

    @Test
    void should_get_login_credentials() {
        String token = "EcoSamplerToken-15";
        LoginCredentials expected = new LoginCredentials(uuid,Role.SCIENTIST,"foo",token);
        LoginCredentials actual = UserMapper.toLoginCredential(user,token);
        assertEquals(expected,actual);
    }
    @Test
    void should_get_selectDTO(){
        UserForSelectDTO expected = new UserForSelectDTO(uuid,"foo",Role.SCIENTIST);
        UserForSelectDTO actual = UserMapper.toUserForSelectorDTO(user);
        assertEquals(expected,actual);
    }

    @Test
    void should_return_auth_user() {
        AuthUser expected = new AuthUser("bar@foo.cx", "test",Role.SCIENTIST);
        AuthUser actual = UserMapper.toAuthUser(user);
        assertEquals(expected.getUsername(),actual.getUsername());
        assertEquals(expected.getPassword(),actual.getPassword());
        assertEquals(expected.getAuthorities(),actual.getAuthorities());
    }
}