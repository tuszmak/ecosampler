package com.codecool.ecosampler.domain;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity(name = "form")
public class Form {
    @Id
    @SequenceGenerator(
            name = "form_id_sequence",
            sequenceName = "form_id_sequence",
            allocationSize = 1
    )
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "form_id_sequence")
    private Long id;

    @Column(name = "name",
            nullable = false)
    private String name;

    @ManyToMany
    private List<Question> questions;

    public Form(String name) {
        this.name = name;
        this.questions = new ArrayList<>();
    }
}
