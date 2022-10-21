package com.lagaltBE.lagaltBE.models.dtos;

import lombok.Data;
import java.util.Set;

@Data
public class ProjectDTO {
    private int id;
    private String title;
    private String description;
    private Set<String> contributors;
    private Set<String> skills;
    private String industry;
    private Set<String> keywords;
    private String status;
}
