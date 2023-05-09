package com.codecool.ecosampler.utilities;

import com.codecool.ecosampler.domain.Role;
import com.codecool.ecosampler.domain.User;
import com.codecool.ecosampler.repository.UserRepository;
import lombok.AllArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
@AllArgsConstructor
public class LineRunner implements CommandLineRunner {
    private UserRepository userRepository;

    @Override
    public void run(String... args) throws Exception {
        User user1 = new User(2L,"Lakatos Pikachu", "alma@sajt.cx", "lovecodecool", Role.DIRECTOR,new ArrayList<>());
        User user2 = new User("Kiera Brown", "kierabrown@example.com", "Qwerty7");
        userRepository.saveAll(List.of(user1, user2));
    }
}
