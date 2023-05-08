package com.codecool.ecosampler.domain;

import jakarta.persistence.*;
import lombok.*;

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

    @ManyToOne
    @JoinColumn(name = "question_id")
    private Question question;

    public Answer(String answer) {
        this.answer = answer;
    }

    public Answer(String answer, Question question) {
        this.answer = answer;
        this.question = question;
    }
}
