package com.lagaltBE.lagaltBE.models.dtos;

import lombok.Data;

import java.util.Set;

@Data
public class ProjectDTO {
    private int id;
    private String title;
    private String description;
    private Set<Integer> contributors;
}
