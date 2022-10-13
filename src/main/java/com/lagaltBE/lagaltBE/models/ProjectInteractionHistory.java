package com.lagaltBE.lagaltBE.models;

import lombok.Getter;
import lombok.Setter;
import javax.persistence.*;

@Getter
@Setter
@Entity
public class ProjectInteractionHistory {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @Column(length=100)
    private String timestamp;
    @Column()
    private Boolean visited;
    @ManyToOne
    @JoinColumn
    private Account account;
    @ManyToOne
    @JoinColumn
    private Project project;
}
