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
@Entity(name = "form")
public class Form {
    @Id
    @SequenceGenerator(
            name = "form_id_sequens",
            sequenceName = "form_id_sequens",
            allocationSize = 1

    )
    @GeneratedValue(strategy = GenerationType.SEQUENCE,generator = "form_id_sequens")
    private Long id;
    @Column(name = "name",
            nullable = false)
    private String name;

    @OneToOne(targetEntity = Project.class)
    private Project project;
}
