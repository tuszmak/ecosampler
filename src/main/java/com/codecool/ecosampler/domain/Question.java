package com.codecool.ecosampler.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.UUID;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity(name = "question")
public class Question {
    @Id
    @JsonIgnore
    @SequenceGenerator(
            name = "question_id_sequence",
            sequenceName = "question_id_sequence",
            allocationSize = 1
    )
    @GeneratedValue(strategy = GenerationType.SEQUENCE,generator = "question_id_sequence")
    private Long id;

    @Column(name = "public_id",
            nullable = false,
            unique = true
    )
    private UUID publicId;

    @Column(name = "description",
            nullable = false,
            unique = true
    )
    private String description;

    @Column(name = "field_style")
    @Enumerated
    private FieldStyle fieldStyle;

    public Question(UUID publicId, String description, FieldStyle fieldStyle) {
        this.publicId = publicId;
        this.description = description;
        this.fieldStyle = fieldStyle;
    }
}
