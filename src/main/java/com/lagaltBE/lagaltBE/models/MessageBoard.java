package com.lagaltBE.lagaltBE.models;

import lombok.Getter;
import lombok.Setter;
import javax.persistence.*;
import java.util.Set;

@Entity
@Getter
@Setter
public class MessageBoard {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @Column(length = 100, nullable = false)
    private String title;
    @Column()
    private String text;
    @Column()
    private String timestamp;
    @Column(length=100)
    private String username;
    @ManyToOne
    @JoinColumn
    private Project project;
    @OneToMany(mappedBy = "messageBoard")
    Set<Message> messages;
}
