package com.lagaltBE.lagaltBE.models;

import lombok.Getter;
import lombok.Setter;
import javax.persistence.*;

@Entity
@Getter
@Setter
public class UserAccount {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @Column(length = 50, nullable = false)
    String name;
    @Column(length = 100, nullable = false)
    String email;
    // Set<Application> applicationHistory;
    // Set<Skill> skills;
    // Set<Contribution> contributions;
    // Set<Contributor> contributor;
}
