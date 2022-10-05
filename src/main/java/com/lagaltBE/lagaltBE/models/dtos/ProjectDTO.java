package com.lagaltBE.lagaltBE.models.dtos;

import lombok.Data;

import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

@Data
public class ProjectDTO {
    private int id;
    private String title;
    private String description;
}
