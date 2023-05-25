package com.codecool.ecosampler.service;

import com.codecool.ecosampler.domain.Role;
import com.codecool.ecosampler.domain.User;
import com.codecool.ecosampler.exeption.NotFoundException;
import com.codecool.ecosampler.repository.UserRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;
import java.util.Optional;
import java.util.Random;
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
    void should_get_no_user_with_role() {
        Role role = Role.DIRECTOR;
        when(userRepository.
                findAllByRole(any(Role.class)))
                .thenReturn(List.of());
        assertEquals(List.of(), userRepository.findAllByRole(role));
//        verify((userRepository).findAllByRole(any(Role.class)));
    }
    @Test
    void should_get_one_user_with_role() {
        Role role = Role.DIRECTOR;
        User user = new User(UUID.randomUUID(),"","",Role.DIRECTOR,"");
        when(userRepository.
                findAllByRole(any(Role.class)))
                .thenReturn(List.of(user));
        List<User> returnUsers = userRepository.findAllByRole(role);
        assertEquals(1, returnUsers.size());
        assertEquals(user, returnUsers.get(0));
        assertEquals(user.getRole(), Role.DIRECTOR);
//        verify((userRepository).findAllByRole(any(Role.class)));
    }
    @Test
    void should_return_users_with_UUIDs(){
        UUID uuid = UUID.randomUUID();
        User user1 = new User(UUID.randomUUID(),"","",Role.DIRECTOR,"");
        User user2 = new User(UUID.randomUUID(),"","",Role.DIRECTOR,"");
        User user3 = new User(UUID.randomUUID(),"","",Role.DIRECTOR,"");
        List<User> users = List.of(user1,user2,user3);
        when(userRepository.
                findAllByPublicIdIn(any()))
                .thenReturn(users);
        List<User> returnUsers = userService.getUsersByPublicId(List.of(uuid));
        assertEquals(users,returnUsers);

    }
    @Test
    void should_get_multiple_users_with_role(){
        Role role = Role.DIRECTOR;
        User user1 = new User(UUID.randomUUID(),"","",Role.DIRECTOR,"");
        User user2 = new User(UUID.randomUUID(),"","",Role.DIRECTOR,"");
        User user3 = new User(UUID.randomUUID(),"","",Role.DIRECTOR,"");
        List<User> users = List.of(user1,user2,user3);
        //  We chose a random user from the repository. All should have the same role
        Random random = new Random();
        int index = random.nextInt(3);
        when(userRepository.
                findAllByRole(any(Role.class)))
                .thenReturn(users);
        List<User> returnUsers = userRepository.findAllByRole(role);
        assertEquals(users, returnUsers);
        assertEquals(returnUsers.get(index).getRole(), Role.DIRECTOR);
    }
//TODO do I need a test that check if we send in a wrong role?
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