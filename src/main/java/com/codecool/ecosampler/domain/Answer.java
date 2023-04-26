package com.codecool.ecosampler.domain;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity(name = "answer")
public class Answer {
    @Id
    @SequenceGenerator(
            name = "answer_id_sequens",
            sequenceName = "answer_id_sequens",
            allocationSize = 1
    )
    @GeneratedValue(strategy = GenerationType.SEQUENCE,generator = "answer_id_sequens")
    private Long id;
    @Column(name = "answer",
            nullable = false)
    private String answer;
}
