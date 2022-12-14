package com.lagaltBE.lagaltBE.models;

import lombok.Getter;
import lombok.Setter;
import javax.persistence.*;

@Entity
@Getter
@Setter
public class Chat {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @Column(length = 300)
    private String text;
    @Column()
    private String timestamp;
    @Column(length = 100)
    private String username;
    @ManyToOne
    @JoinColumn
    private Project project;
}
