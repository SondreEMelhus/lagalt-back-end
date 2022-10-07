package com.lagaltBE.lagaltBE.models;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.Set;

@Entity
@Getter
@Setter
public class Skill {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @Column(length = 100, nullable = false)
    private String title;
    @ManyToMany(mappedBy = "skills")
    private Set<Account> accounts;
    @ManyToMany(mappedBy = "skills")
    private Set<Project> projects;
}
