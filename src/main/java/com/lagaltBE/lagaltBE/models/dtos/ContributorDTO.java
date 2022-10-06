package com.lagaltBE.lagaltBE.models.dtos;

import com.lagaltBE.lagaltBE.models.Project;
import lombok.Data;

@Data
public class ContributorDTO {
    private int id;
    private String role;
    private int project;
}
