package com.lagaltBE.lagaltBE.models;

import lombok.Getter;
import lombok.Setter;
import javax.persistence.*;
import java.util.Set;

@Entity
@Getter
@Setter
public class Account {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @Column(length = 50, nullable = false)
    String name;
    @Column(length = 100, nullable = false)
    String email;

    @OneToMany(mappedBy = "account")
    Set<Contributor> contributors;

    // Set<Application> applicationHistory;
    // Set<Skill> skills;
    // Set<Contributor> contributor;
}
