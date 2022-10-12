package com.lagaltBE.lagaltBE.models;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.Set;

@Entity
@Getter
@Setter
public class StatusUpdateBoard {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @Column(length = 100, nullable = false)
    private String title;
    @Column()
    private String text;
    @Column()
    private String timestamp;
    @ManyToOne
    @JoinColumn
    private Account account;
    @ManyToOne
    @JoinColumn
    private Project project;
    @OneToMany(mappedBy = "statusUpdateBoard")
    Set<StatusUpdate> statusUpdates;
}
