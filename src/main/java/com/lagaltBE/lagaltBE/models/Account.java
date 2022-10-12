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
    @Column(length = 50)
    private String firstName;
    @Column(length = 50)
    private String lastName;
    @Column(length = 50, nullable = false, unique = true)
    private String username;
    @Column(length = 100, nullable = false, unique = true)
    private String email;
    @Column()
    private boolean visible;
    @Column(length = 200)
    private String description;
    @OneToMany(mappedBy = "account")
    Set<Contributor> contributors;
    @OneToMany(mappedBy = "account")
    Set<Application> applications;
    @OneToMany(mappedBy = "account")
    Set<Chat> chats;
    @OneToMany(mappedBy = "account")
    Set<MessageBoard> messageBoards;
    @OneToMany(mappedBy = "account")
    Set<Message> messages;
    @OneToMany(mappedBy = "account")
    Set<StatusUpdateBoard> statusUpdateBoards;
    @OneToMany(mappedBy = "account")
    Set<StatusUpdate> statusUpdate;
    @ManyToMany
    @JoinTable(
            name = "account_skill",
            joinColumns = {@JoinColumn(name = "account_id")},
            inverseJoinColumns = {@JoinColumn(name = "skill_id")}
    )
    private Set<Skill> skills;
    // Set<Contribution> contributions;
}
