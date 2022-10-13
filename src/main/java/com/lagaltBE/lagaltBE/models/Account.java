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
    @Column(length = 50, nullable = false, unique = true)
    private String username;
    @Column()
    private boolean visible;
    @Column(length = 200)
    private String description;
    @Column(length = 200)
    private String portfolio;
    @OneToMany(mappedBy = "account")
    Set<Contributor> contributors;
    @OneToMany(mappedBy = "account")
    Set<Application> applications;
    @OneToMany(mappedBy = "account")
    Set<ProjectInteractionHistory> projectInteractionHistory;
    @ManyToMany
    @JoinTable(
            name = "account_skill",
            joinColumns = {@JoinColumn(name = "account_id")},
            inverseJoinColumns = {@JoinColumn(name = "skill_id")}
    )
    private Set<Skill> skills;
}
