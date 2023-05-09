package com.codecool.ecosampler.utilities;

import com.codecool.ecosampler.domain.Project;
import com.codecool.ecosampler.domain.Role;
import com.codecool.ecosampler.domain.User;
import com.codecool.ecosampler.repository.ProjectRepository;
import com.codecool.ecosampler.repository.UserRepository;
import lombok.AllArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

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
        User user1 = new User(UUID.randomUUID(), "Mr. Moneybanks", "ilikemoney@dollar.us", "elonmusk69");
        user1.setRole(Role.DIRECTOR);
        User user2 = new User(UUID.randomUUID(), "Kiera Brown", "kierabrown@example.com", "Qwerty7");
        user2.setRole(Role.PROJECT_LEADER);
        User user3 = new User(UUID.randomUUID(), "Lakatos Pikachu", "alma@sajt.cx", "lovecodecool");
        user3.setRole(Role.SCIENTIST);
        userRepository.saveAll(List.of(user1, user2, user3));

        project1.addUserToProject(user2);
        projectRepository.save(project1);

    }
}
