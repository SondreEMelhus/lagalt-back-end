package com.lagaltBE.lagaltBE.models.dtos;

import com.lagaltBE.lagaltBE.models.Industry;
import lombok.Data;

import java.util.Set;

@Data
public class ProjectDTO {
    private int id;
    private String title;
    private String description;
    private Set<String> contributors;
    private Set<String> skills ;
    private int industry;
}
