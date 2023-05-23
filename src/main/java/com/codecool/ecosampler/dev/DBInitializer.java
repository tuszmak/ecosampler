package com.codecool.ecosampler.dev;

import com.codecool.ecosampler.domain.*;
import com.codecool.ecosampler.repository.*;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@Component
@RequiredArgsConstructor
public class DBInitializer implements CommandLineRunner {
    private final AnswerRepository answerRepository;
    private final UserRepository userRepository;
    private final ProjectRepository projectRepository;
    private final FormRepository formRepository;
    private final QuestionRepository questionRepository;
    private final SampleDataRepository sampleDataRepository;
    private final PasswordEncoder passwordEncoder;

    @Override
    public void run(String... args) throws Exception {

        Project project1 = new Project(UUID.randomUUID(), "Researching of beavers", "We research beavers.");

        User user1 = new User(UUID.randomUUID(), "Mr. Moneybanks", "ilikemoney@dollar.us", Role.DIRECTOR, passwordEncoder.encode("elonmusk69"));
        User user2 = new User(UUID.randomUUID(), "Kiera Brown", "kierabrown@example.com", Role.PROJECT_LEADER, passwordEncoder.encode("Qwerty7"));
        User user3 = new User(UUID.randomUUID(), "Gerco Timmerman", "gti@example.com", Role.PROJECT_LEADER, passwordEncoder.encode("Qwerty7"));
        User user4 = new User(UUID.randomUUID(), "Lakatos Pikachu", "alma@sajt.cx", Role.SCIENTIST, passwordEncoder.encode("lovecodecool"));
        User user5 = new User(UUID.randomUUID(), "Lakatos Endre", "korte@sajt.cx", Role.SCIENTIST, passwordEncoder.encode("lovecodecool"));

        Form form1 = new Form(UUID.randomUUID(), "Appearance");
        Form form2 = new Form(UUID.randomUUID(), "Size");

        Question question1 = new Question(UUID.randomUUID(), "What color is it?", FieldStyle.SHORT_TEXT);
        Answer answer = new Answer(UUID.randomUUID(), "Brown", question1);
        SampleData sampleData = new SampleData(UUID.randomUUID(), LocalDateTime.now(), user3, form1, List.of(answer));

        project1.addUserToProject(user2);
        project1.addFormToProject(form1);
        project1.addFormToProject(form2);
        form1.addQuestion(question1);
        answer.setSampleData(sampleData);
        userRepository.saveAll(List.of(user1, user2, user3, user4, user5));
        questionRepository.save(question1);
        formRepository.saveAll(List.of(form1, form2));
        projectRepository.save(project1);
        sampleDataRepository.save(sampleData);
        answerRepository.save(answer);

    }
}
