package com.lagaltBE.lagaltBE.models;

import lombok.Getter;
import lombok.Setter;
import javax.persistence.*;
import java.util.Set;

@Entity
@Getter
@Setter
public class Keyword {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @Column(length = 100, nullable = false)
    private String title;
    @ManyToMany
    @JoinTable(
            name = "keyword_industry",
            joinColumns = {@JoinColumn(name = "keyword_id")},
            inverseJoinColumns = {@JoinColumn(name = "industry_id")}
    )
    private Set<Industry> industries;
    @ManyToMany(mappedBy = "keywords")
    private Set<Project> projects;
}
