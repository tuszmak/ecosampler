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
@Entity(name = "question")
public class Question {
    @Id
    @SequenceGenerator(
            name = "question_id_sequens",
            sequenceName = "question_id_sequens",
            allocationSize = 1
    )
    @GeneratedValue(strategy = GenerationType.SEQUENCE,generator = "question_id_sequens")
    private Long id;

    @Column(name = "description",
            nullable = false,
            unique = true
    )
    private String description;

    @Column(name = "field_style")
    @Enumerated
    private FieldStyle fieldStyle;
}
