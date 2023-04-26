package com.codecool.ecosampler.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;
import java.util.Objects;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity(name = "\"user\"")
public class User {

    @Id
    @SequenceGenerator(
            name = "user_id_sequens",
            sequenceName = "user_id_sequens",
             allocationSize = 1
    )
    @GeneratedValue(strategy = GenerationType.SEQUENCE,generator = "user_id_sequens")
    private Long id;

    @Column(name = "email",
            nullable = false,
            unique = true
    )
    private String email;

    @Column(name = "name",
            nullable = false
    )
    private String name;

    @Column(name = "password",
            nullable = false
    )
    @JsonIgnore
    private String password;

    @Column(name = "role")
    @Enumerated
    private Role role;

    @ManyToMany
    @JoinTable(name = "user_to_project_map",
                joinColumns = @JoinColumn(name = "user_id"),
                inverseJoinColumns = @JoinColumn(name = "project_id")
    )
    private List<Project> projects;

    public User(String email, String name, String password) {
        this.email = email;
        this.name = name;
        this.password = password;
    }
}
