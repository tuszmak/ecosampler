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
@Entity(name = "answer")
public class Answer {
    @Id
    @JsonIgnore
    @SequenceGenerator(
            name = "answer_id_sequence",
            sequenceName = "answer_id_sequence",
            allocationSize = 1
    )
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "answer_id_sequence")
    private Long id;

    @Column(name = "answer",
            nullable = false
    )
    private String answer;

    @Column(name = "public_id",
            nullable = false,
            unique = true
    )
    private UUID publicId;

    @ManyToOne
    @JoinColumn(name = "question_id")
    private Question question;
    @ManyToOne
    @JoinColumn(name = "sample_data_id")
    private SampleData sampleData;

    public Answer(UUID publicId, String answer, Question question, SampleData sampleData) {
        this.publicId = publicId;
        this.answer = answer;
        this.question = question;
        this.sampleData = sampleData;
    }
}
