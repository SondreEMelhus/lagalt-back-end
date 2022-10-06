package com.lagaltBE.lagaltBE.models;

import lombok.Getter;
import lombok.Setter;
import javax.persistence.*;
import java.util.Set;

@Entity
@Getter
@Setter
public class UserAccount {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @Column(length = 50, nullable = false)
    private String name;
    @Column(length = 100, nullable = false)
    private String email;
    @ManyToMany
    @JoinTable(
            name = "user_account_skill",
            joinColumns = {@JoinColumn(name = "user_account_id")},
            inverseJoinColumns = {@JoinColumn(name = "skill_id")}
    )
    private Set<Skill> skills;
    @Column()
    private boolean visible;
    // Set<Application> applicationHistory;
    // Set<Contribution> contributions;
    //@OneToMany(mappedBy = "user_account")
    //Set<Contributor> contributors;
}
