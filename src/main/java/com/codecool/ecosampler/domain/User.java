package com.codecool.ecosampler.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;
import java.util.UUID;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity(name = "\"user\"")
public class User {

    @Id
    @JsonIgnore
    @SequenceGenerator(
            name = "user_id_sequence",
            sequenceName = "user_id_sequence",
            allocationSize = 1
    )
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "user_id_sequence")
    private Long id;

    @Column(name = "public_id",
            nullable = false,
            unique = true
    )
    private UUID publicId;

    @Column(name = "name",
            nullable = false
    )
    private String name;

    @Column(name = "email",
            nullable = false,
            unique = true
    )
    private String email;

    @Column(name = "password",
            nullable = false
    )
    @JsonIgnore
    private String password;

    @Column(name = "role")
    @Enumerated
    private Role role;

    @ManyToMany(mappedBy = "users")
    private List<Project> projects;


    public User(UUID publicId, String name, String email, String password) {
        this.publicId = publicId;
        this.email = email;
        this.name = name;
        this.password = password;
    }
}
