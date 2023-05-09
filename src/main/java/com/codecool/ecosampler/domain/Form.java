package com.codecool.ecosampler.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity(name = "form")
public class Form {
    @Id
    @JsonIgnore
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

    @Column(name = "public_id",
            nullable = false,
            unique = true
    )
    private UUID publicId;

    @ManyToMany
    private List<Question> questions;

    public Form(UUID publicId, String name) {
        this.publicId = publicId;
        this.name = name;
        this.questions = new ArrayList<>();
    }
}
