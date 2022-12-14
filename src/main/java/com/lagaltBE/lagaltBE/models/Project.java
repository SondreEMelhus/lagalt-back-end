package com.lagaltBE.lagaltBE.models;

import lombok.Getter;
import lombok.Setter;
import javax.persistence.*;
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
    @Column(length = 100)
    private String status;
    @OneToMany(mappedBy = "project")
    Set<Contributor> contributors;
    @OneToMany(mappedBy = "project")
    Set<Application> applications;
    @OneToMany(mappedBy = "project")
    Set<Chat> chats;
    @OneToMany(mappedBy = "project")
    Set<MessageBoard> messageBoards;
    @OneToMany(mappedBy = "project")
    Set<StatusUpdateBoard> statusUpdateBoards;
    @OneToMany(mappedBy = "project")
    Set<ProjectInteractionHistory> projectInteractionHistory;
    @ManyToMany
    @JoinTable(
            name = "skill_project",
            joinColumns = {@JoinColumn(name = "project_id")},
            inverseJoinColumns = {@JoinColumn(name = "skill_id")}
    )
    private Set<Skill> skills;
    @ManyToOne
    @JoinColumn(nullable = false)
    private Industry industry;
    @ManyToMany
    @JoinTable(
            name = "keyword_project",
            joinColumns = {@JoinColumn(name = "project_id")},
            inverseJoinColumns = {@JoinColumn(name = "keyword_id")}
    )
    private Set<Keyword> keywords;
}