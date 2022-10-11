package com.lagaltBE.lagaltBE.models.dtos;

import lombok.Data;
import java.util.Set;

@Data
public class IndustryDTO {
    private int id;
    private String title;
    private Set<Integer> projects;
    private Set<String> keywords;
}
