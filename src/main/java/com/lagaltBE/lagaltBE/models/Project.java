package com.lagaltBE.lagaltBE.models;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Getter
@Setter
@Entity
public class Project {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @Column(length = 50)
    private String title;
    @Column(length = 500)
    private String description;

    @OneToMany(mappedBy = "project")
    Set<Contributor> contributors;
    @ManyToMany
    @JoinTable(
            name = "skill_project",
            joinColumns = {@JoinColumn(name = "project_id")},
            inverseJoinColumns = {@JoinColumn(name = "skill_id")}
    )
    private Set<Skill> skills;

    public HashSet<Account> getAccounts() {
        HashSet<Account> accounts = new HashSet<>();
        for (Contributor c: contributors) {
            accounts.add(c.getAccount());
        }
        return accounts;
    }

    @Override
    public String toString() {
        return "Project{" +
                "id=" + id +
                ", name='" + title + '\'' +
                '}';
    }
}