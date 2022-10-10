package com.lagaltBE.lagaltBE.models.dtos;

import com.lagaltBE.lagaltBE.models.Industry;
import lombok.Data;

import java.util.Set;

@Data
public class ProjectDTO {
    private int id;
    private String title;
    private String description;
    private Set<Integer> contributors;
    private Set<Integer> skills;
    private int industry;
}
