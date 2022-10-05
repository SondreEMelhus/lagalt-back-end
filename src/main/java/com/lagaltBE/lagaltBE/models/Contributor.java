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
    // bør endres til enum!!!!!!
    @Column(length = 50)
    private String role;
    //@ManyToOne
    //@JoinColumn
    //private UserAccount userAccount;
    @ManyToOne
    @JoinColumn
    private Project project;
}

