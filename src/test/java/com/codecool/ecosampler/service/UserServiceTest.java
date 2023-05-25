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
        when(userRepository.
                findByPublicId(any(UUID.class)))
                .thenReturn(Optional.of(user));
        assertEquals(User.class, userService.getUserByPublicId(UUID.randomUUID()).getClass());
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
    void getUserDTOByPublicId() {
    }

    @Test
    void registerUser() {
    }

    @Test
    void deleteUserByPublicId() {
    }

    @Test
    void getUserByPublicId() {
    }

    @Test
    void getAllUserByRole() {
    }

    @Test
    void getUserByEmail() {
    }
}