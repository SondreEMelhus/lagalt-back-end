package com.lagaltBE.lagaltBE.models;

import lombok.Getter;
import lombok.Setter;
import javax.persistence.*;

@Entity
@Getter
@Setter
public class Message {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @Column()
    private String text;
    @Column()
    private String timestamp;
    @ManyToOne
    @JoinColumn
    private MessageBoard messageBoard;
    @Column(length=100)
    private String username;
}
