package com.lagaltBE.lagaltBE.models;

import lombok.Getter;
import lombok.Setter;
import javax.persistence.*;

@Entity
@Getter
@Setter
public class Contributor {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @Column(length = 50)
    private String role; // is either 'Owner', 'Admin' or 'Member'
    @ManyToOne
    @JoinColumn
    private Account account;
    @ManyToOne
    @JoinColumn
    private Project project;
}

