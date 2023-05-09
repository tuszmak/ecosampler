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
@Entity(name = "project")
public class Project {
    @Id
    @JsonIgnore
    @SequenceGenerator(
            name = "project_id_sequence",
            sequenceName = "project_id_sequence",
            allocationSize = 1
    )
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "project_id_sequence")
    private Long id;

    @Column(name = "public_id",
            nullable = false,
            unique = true
    )
    private UUID publicId;

    @Column(name = "name",
            nullable = false,
            unique = true
    )
    private String name;

    @Column(name = "description")
    private String description;

    @OneToMany
    private List<Form> formList;

    @JsonIgnore
    @ManyToMany
    @JoinTable(name = "user_to_project_map",
            joinColumns = @JoinColumn(name = "project_id"),
            inverseJoinColumns = @JoinColumn(name = "user_id")
    )
    private List<User> users;

    public Project(UUID publicId, String name, String description) {
        this.publicId = publicId;
        this.name = name;
        this.description = description;
        this.formList = new ArrayList<>();
        this.users = new ArrayList<>();
    }

    public boolean addUserToProject(User user) {
       return this.users.add(user);
    }
}
