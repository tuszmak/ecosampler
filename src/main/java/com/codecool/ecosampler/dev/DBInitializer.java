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
    private final UserRepository userRepository;
    private final ProjectRepository projectRepository;
    private final FormRepository formRepository;
    private final QuestionRepository questionRepository;
    private final PasswordEncoder passwordEncoder;
    private final SampleDataRepository sampleDataRepository;
    private final AnswerRepository answerRepository;

    @Override
    public void run(String... args) throws Exception {

        Project project1 = new Project(UUID.randomUUID(), "Researching of beavers", "We research beavers.");

        User user1 = new User(UUID.randomUUID(), "Mr. Director", "director@codecool.com", Role.DIRECTOR, passwordEncoder.encode("director"));
        User user2 = new User(UUID.randomUUID(), "Kiera Brown", "kierabrown@codecool.com", Role.PROJECT_LEADER, passwordEncoder.encode("kierabrown"));
        User user3 = new User(UUID.randomUUID(), "Gerco Timmerman", "gti@codecool.com", Role.PROJECT_LEADER, passwordEncoder.encode("gti"));
        User user4 = new User(UUID.randomUUID(), "Lakatos Istvan", "lakatosistvan@codecool.com", Role.SCIENTIST, passwordEncoder.encode("lakatosistvan"));
        User user5 = new User(UUID.randomUUID(), "Lakatos Endre", "lakatosendre@codecool.com", Role.SCIENTIST, passwordEncoder.encode("lakatosendre"));

        Form form1 = new Form(UUID.randomUUID(), "Appearance");
        Form form2 = new Form(UUID.randomUUID(), "Size");

        Question question1 = new Question(UUID.randomUUID(), "What color is it?", FieldStyle.SHORT_TEXT);
        SampleData sampleData = new SampleData(UUID.randomUUID(), LocalDateTime.now(), user3, form1);
        Answer answer = new Answer(UUID.randomUUID(), "Brown", question1, sampleData);

        project1.addUserToProject(user2);
        project1.addFormToProject(form1);
        project1.addFormToProject(form2);
        project1.addUserToProject(user4);
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
