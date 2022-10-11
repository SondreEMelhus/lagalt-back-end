package com.lagaltBE.lagaltBE.models;

import lombok.Getter;
import lombok.Setter;
import javax.persistence.*;

@Entity
@Getter
@Setter
public class Application {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @Column(length = 200, nullable = false)
    private String motivation;
    // Status is initially set to 'Pending'. An admin can set the applications to either 'Accepted' or 'Denied'
    @Column(columnDefinition = "varchar(20) default 'Pending'")
    private String status;
    @ManyToOne
    @JoinColumn
    private Account account;
    @ManyToOne
    @JoinColumn
    private Project project;
}
