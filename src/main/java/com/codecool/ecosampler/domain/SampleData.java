package com.codecool.ecosampler.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity(name = "sample_data")
public class SampleData {
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

    @Column(name = "time",
            nullable = false
    )
    private LocalDateTime time;

    @OneToOne(targetEntity = User.class)
    private User user;

    @OneToOne(targetEntity = Form.class)
    private Form form;

    @OneToMany(mappedBy = "sampleData",
    cascade = CascadeType.ALL)
    private List<Answer> answers = new ArrayList<>();

    public SampleData(UUID publicId, LocalDateTime time, User user, Form form) {
        this.publicId = publicId;
        this.time = time;
        this.user = user;
        this.form = form;
    }
}
