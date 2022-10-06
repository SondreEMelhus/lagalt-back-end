package com.lagaltBE.lagaltBE.models.dtos;

import com.lagaltBE.lagaltBE.models.Skill;
import lombok.Data;

import java.util.Set;

@Data
public class UserAccountDTO {
    private int id;
    private String name;
    private String email;
    private boolean visible;
    private Set<Skill> skills;
}
