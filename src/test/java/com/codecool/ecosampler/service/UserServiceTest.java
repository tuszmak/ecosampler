package com.codecool.ecosampler.service;

import com.codecool.ecosampler.domain.User;
import com.codecool.ecosampler.exeption.NotFoundException;
import com.codecool.ecosampler.repository.UserRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.any;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class UserServiceTest {

    @Mock
    private UserRepository userRepository;
    @InjectMocks
    private UserService userService;

    @Test
    void should_return_one_user_with_UUID() {
        User user = new User();
        UUID uuid = UUID.randomUUID();
        when(userRepository.
                findByPublicId(any(UUID.class)))
                .thenReturn(Optional.of(user));
        User returnUser = userService.getUserByPublicId(uuid);
        assertEquals(user, returnUser);
    }
    @Test
    void should_throw_error_when_finding_user(){
        UUID uuid = UUID.randomUUID();
        when(userRepository.
                findByPublicId(any(UUID.class)))
                .thenReturn(Optional.empty());
        assertThrows(NotFoundException.class, ()-> userService.getUserByPublicId(uuid));
    }
    @Test
    void should_get_user_by_email() {
        User user = new User();
        when(userRepository.
                findByEmail(any(String.class)))
                .thenReturn(Optional.of(user));
        User returnUser = userService.getUserByEmail("a@a.cx");
        assertEquals(user,returnUser);

    }
    @Test
    void should_throw_error_when_finding_with_email() {
        when(userRepository.
                findByEmail(any(String.class)))
                .thenReturn(Optional.empty());
        assertThrows(NotFoundException.class, ()-> userService.getUserByEmail("a@a.cx"));

    }
    @Test
    void registerUser() {
    }

    @Test
    void deleteUserByPublicId() {
    }

    @Test
    void getAllUserByRole() {
    }

    @Test
    void getUserByEmail() {
    }
}