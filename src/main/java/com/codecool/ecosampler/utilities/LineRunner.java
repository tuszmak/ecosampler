package com.codecool.ecosampler.utilities;

import com.codecool.ecosampler.domain.*;
import com.codecool.ecosampler.repository.FormRepository;
import com.codecool.ecosampler.repository.ProjectRepository;
import com.codecool.ecosampler.repository.QuestionRepository;
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
    private FormRepository formRepository;
    private QuestionRepository questionRepository;

    @Override
    public void run(String... args) throws Exception {

        Project project1 = new Project(UUID.randomUUID(), "Researching of beavers", "We research beavers.");
        User user1 = new User(UUID.randomUUID(), "Mr. Moneybanks", "ilikemoney@dollar.us", "elonmusk69");
        user1.setRole(Role.DIRECTOR);
        User user2 = new User(UUID.randomUUID(), "Kiera Brown", "kierabrown@example.com", "Qwerty7");
        user2.setRole(Role.PROJECT_LEADER);
        User user3 = new User(UUID.randomUUID(), "Lakatos Pikachu", "alma@sajt.cx", "lovecodecool");
        user3.setRole(Role.SCIENTIST);
        Form form1 = new Form(UUID.randomUUID(), "Appearance");
        Form form2 = new Form(UUID.randomUUID(), "Size");
        Question question1 = new Question(UUID.randomUUID(), "What color is it?", FieldStyle.SHORT_TEXT);
        form1.addQuestion(question1);

        project1.addUserToProject(user2);
        project1.addFormToProject(form2);
        userRepository.saveAll(List.of(user1, user2, user3));
        questionRepository.save(question1);
        formRepository.saveAll(List.of(form1, form2));
        projectRepository.save(project1);

    }
}
