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
@Entity(name = "user")
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

    @Column(name = "role")
    @Enumerated
    private Role role;
}
