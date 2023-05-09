package com.codecool.ecosampler.utilities;

import com.codecool.ecosampler.domain.Project;
import com.codecool.ecosampler.domain.Role;
import com.codecool.ecosampler.domain.User;
import com.codecool.ecosampler.repository.ProjectRepository;
import com.codecool.ecosampler.repository.UserRepository;
import lombok.AllArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Component
@AllArgsConstructor
public class LineRunner implements CommandLineRunner {
    private UserRepository userRepository;
    private ProjectRepository projectRepository;

    @Override
    public void run(String... args) throws Exception {
        Project project1 = new Project(UUID.randomUUID(), "Researching of beavers", "We research beavers.");
        User user1 = new User(2L, UUID.randomUUID(), "Mr. Moneybanks", "ilikemoney@dollar.us", "elonmusk69", Role.DIRECTOR, new ArrayList<>());
        User user2 = new User(2L, UUID.randomUUID(), "Kiera Brown", "kierabrown@example.com", "Qwerty7", Role.PROJECT_LEADER, new ArrayList<>());
        User user3 = new User(3L, UUID.randomUUID(), "Lakatos Pikachu", "alma@sajt.cx", "lovecodecool", Role.SCIENTIST, new ArrayList<>());

        userRepository.saveAll(List.of(user1, user2, user3));
//        project1.addUserToProject(user3);
//        projectRepository.save(project1);
    }
}
