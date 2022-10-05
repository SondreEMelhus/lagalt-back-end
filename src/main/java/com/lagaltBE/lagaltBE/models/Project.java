package com.lagaltBE.lagaltBE.models;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

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

    @Override
    public String toString() {
        return "Project{" +
                "id=" + id +
                ", name='" + title + '\'' +
                '}';
    }
}