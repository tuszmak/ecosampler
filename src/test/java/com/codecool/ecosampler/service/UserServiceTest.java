package com.codecool.ecosampler.service;

import com.codecool.ecosampler.controller.dto.user.NewUser;
import com.codecool.ecosampler.controller.dto.user.UserDTO;
import com.codecool.ecosampler.controller.dto.user.UserForSelectDTO;
import com.codecool.ecosampler.domain.Role;
import com.codecool.ecosampler.domain.User;
import com.codecool.ecosampler.exception.BadRequestException;
import com.codecool.ecosampler.exception.NotFoundException;
import com.codecool.ecosampler.repository.UserRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.List;
import java.util.Optional;
import java.util.Random;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class UserServiceTest {

    private UUID uuid;
    @Mock
    private UserRepository userRepository;
    @Mock
    private PasswordEncoder passwordEncoder;
    @InjectMocks
    private UserService userService;


    @BeforeEach
    void setup() {
        uuid = UUID.randomUUID();
    }

    //getUserByPublicId tests
    @Test
    void should_return_one_user_with_UUID() {
        User user = new User();
        when(userRepository.findByPublicId(any(UUID.class))).thenReturn(Optional.of(user));
        User returnUser = userService.getUserByPublicId(uuid);
        assertEquals(user, returnUser);
        verify(userRepository).findByPublicId(any(UUID.class));
    }

    @Test
    void should_throw_error_when_finding_user() {
        when(userRepository.findByPublicId(any(UUID.class))).thenReturn(Optional.empty());
        assertThrows(NotFoundException.class, () -> userService.getUserByPublicId(uuid));
        verify(userRepository).findByPublicId(any(UUID.class));
    }

    //getUserByEmail tests
    @Test
    void should_get_user_by_email() {
        User user = new User();
        when(userRepository.findByEmail(any(String.class))).thenReturn(Optional.of(user));
        User returnUser = userService.getUserByEmail("a@a.cx");
        assertEquals(user, returnUser);
        verify(userRepository).findByEmail(any(String.class));

    }

    @Test
    void should_throw_error_when_finding_with_email() {
        when(userRepository.findByEmail(any(String.class))).thenReturn(Optional.empty());
        assertThrows(NotFoundException.class, () -> userService.getUserByEmail("a@a.cx"));
        verify(userRepository).findByEmail(any(String.class));
    }

    //getUserDTOByPublicId tests
    @Test
    void should_get_no_userDTO_by_publicID() {
        when(userRepository.findByPublicId(any(UUID.class))).thenReturn(Optional.empty());
        assertThrows(NotFoundException.class, () -> userService.getUserDTOByPublicId(uuid));

    }

    @Test
    void should_get_userDTO_by_publicID() {
        User user = new User(uuid, "", "", Role.DIRECTOR, "");
        when(userRepository.findByPublicId(any(UUID.class))).thenReturn(Optional.of(user));
        UserDTO expected = new UserDTO(uuid, "", "", Role.DIRECTOR);
        UserDTO actual = userService.getUserDTOByPublicId(uuid);
        assertEquals(expected, actual);
        verify(userRepository).findByPublicId(any());

    }

    //getAllUserByRole tests
    @Test
    void should_get_no_user_with_role() {
        Role role = Role.DIRECTOR;
        when(userRepository.findAllByRole(any(Role.class))).thenReturn(List.of());
        assertEquals(List.of(), userService.getAllUserByRole(role));
        verify(userRepository).findAllByRole(any(Role.class));
    }

    //findAllByRole tests
    @Test
    void should_get_multiple_users_with_role() {
        Role role = Role.DIRECTOR;
        User user1 = new User(uuid, "", "", role, "");
        User user2 = new User(uuid, "", "", role, "");
        User user3 = new User(uuid, "", "", role, "");
        List<User> users = List.of(user1, user2, user3);
        //  We chose a random user from the repository. All should have the same role
        Random random = new Random();
        int index = random.nextInt(3);
        when(userRepository.findAllByRole(any(Role.class))).thenReturn(users);
        List<User> returnUsers = userService.getAllUserByRole(role);
        assertEquals(users, returnUsers);
        assertEquals(returnUsers.get(index).getRole(), role);
        verify(userRepository).findAllByRole(any(Role.class));
    }

    @Test
    void should_get_one_user_with_role() {
        Role role = Role.DIRECTOR;
        User user = new User(UUID.randomUUID(), "", "", Role.DIRECTOR, "");
        when(userRepository.findAllByRole(any(Role.class))).thenReturn(List.of(user));
        List<User> returnUsers = userRepository.findAllByRole(role);
        assertEquals(1, returnUsers.size());
        assertEquals(user, returnUsers.get(0));
        assertEquals(user.getRole(), Role.DIRECTOR);
        verify(userRepository).findAllByRole(any(Role.class));
    }

    //getUsersForSelectDTOByRole tests
    @Test
    void should_return_nothing_with_role_director() {
        Role role = Role.DIRECTOR;
        when(userRepository.findAllByRole(any(Role.class))).thenReturn(List.of());
        List<UserForSelectDTO> expected = List.of();
        List<UserForSelectDTO> actual = userService.getUsersForSelectDTOByRole(role);
        assertEquals(expected, actual);
        verify(userRepository).findAllByRole(any(Role.class));
    }

    @Test
    void should_return_users_with_UUIDs() {
        User user1 = new User(uuid, "", "", Role.DIRECTOR, "");
        User user2 = new User(uuid, "", "", Role.DIRECTOR, "");
        User user3 = new User(uuid, "", "", Role.DIRECTOR, "");
        List<User> users = List.of(user1, user2, user3);
        when(userRepository.findAllByPublicIdIn(any())).thenReturn(users);
        List<User> returnUsers = userService.getUsersByPublicId(List.of(uuid));
        assertEquals(users, returnUsers);
        verify(userRepository).findAllByPublicIdIn(any());
    }

    @Test
    void should_return_selectDTOs_with_role_director() {
        Role role = Role.DIRECTOR;
        User user1 = new User(uuid, "", "", role, "");
        User user2 = new User(uuid, "", "", role, "");
        User user3 = new User(uuid, "", "", role, "");
        UserForSelectDTO userForSelectDTO1 = new UserForSelectDTO(uuid, "", Role.DIRECTOR);
        UserForSelectDTO userForSelectDTO2 = new UserForSelectDTO(uuid, "", Role.DIRECTOR);
        UserForSelectDTO userForSelectDTO3 = new UserForSelectDTO(uuid, "", Role.DIRECTOR);
        List<User> users = List.of(user1, user2, user3);
        when(userRepository.findAllByRole(any(Role.class))).thenReturn(users);

        List<UserForSelectDTO> expected = List.of(userForSelectDTO1, userForSelectDTO2, userForSelectDTO3);
        List<UserForSelectDTO> actual = userService.getUsersForSelectDTOByRole(role);

        assertEquals(expected, actual);
        verify(userRepository).findAllByRole(any(Role.class));
    }

    //deleteUserByPublicId
    @Test
    void should_delete_nothing() {
        when(userRepository.findByPublicId(uuid)).thenReturn(Optional.empty());
        assertThrows(NotFoundException.class, () -> userService.deleteUserByPublicId(uuid));
        verify(userRepository).findByPublicId(any());
    }

    @Test
    void should_delete_a_user() {
        Role role = Role.DIRECTOR;
        User user1 = new User(uuid, "", "", role, "");
        when(userRepository.findByPublicId(uuid)).thenReturn(Optional.of(user1));
        userService.deleteUserByPublicId(uuid);
        verify(userRepository).deleteById(any());
    }

    //registerUser tests
    @Test
    void should_throw_error_on_register() {
        NewUser newUser = new NewUser("", "", Role.DIRECTOR, "");
        when(userRepository.existsUserByEmail(any(String.class))).thenReturn(true);
        assertThrows(BadRequestException.class, () -> userService.registerUser(newUser));
    }

    @Test
    void should_register_a_user() {
        NewUser newUser = new NewUser("", "", Role.DIRECTOR, "");
        User user = new User(uuid, "", "", Role.DIRECTOR, "");
        when(userRepository.save(any(User.class))).thenReturn(user);
        when(passwordEncoder.encode(any(String.class))).thenReturn("");

        UserDTO expected = new UserDTO(uuid, "", "", Role.DIRECTOR);
        UserDTO actual = userService.registerUser(newUser);
        assertEquals(expected, actual);
        verify(userRepository).save(any());
        verify(passwordEncoder).encode(any());
    }

}