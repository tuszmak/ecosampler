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
@Entity(name = "project")
public class Project {
    @Id
    @SequenceGenerator(
            name = "project_id_sequens",
            sequenceName = "project_id_sequens",
            allocationSize = 1

    )
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "project_id_sequens")
    private Long id;

    @Column(name = "name",
            nullable = false,
            unique = true
    )
    private String name;

    @Column(name = "description")
    private String description;

}
