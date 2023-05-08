package com.codecool.ecosampler.domain;

import jakarta.persistence.*;
import lombok.*;

import java.util.UUID;

@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
@Entity(name = "answer")
public class Answer {
    @Id
    @SequenceGenerator(
            name = "answer_id_sequence",
            sequenceName = "answer_id_sequence",
            allocationSize = 1
    )
    @GeneratedValue(strategy = GenerationType.SEQUENCE,generator = "answer_id_sequence")
    private Long id;
    @Column(name = "answer",
            nullable = false)
    private String answer;

    @Column(name = "public_id",
            nullable = false,
            unique = true
    )
    private UUID publicId;

    @ManyToOne
    @JoinColumn(name = "question_id")
    private Question question;

    public Answer(String answer) {
        this.answer = answer;
    }

    public Answer(UUID publicId, String answer, Question question) {
        this.publicId = publicId;
        this.answer = answer;
        this.question = question;
    }
}
