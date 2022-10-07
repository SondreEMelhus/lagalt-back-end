package com.lagaltBE.lagaltBE.models.dtos;

import com.lagaltBE.lagaltBE.models.Skill;
import com.lagaltBE.lagaltBE.models.UserAccount;
import lombok.Data;
import java.util.Set;

@Data
public class SkillDTO {
    private int id;
    private String title;
    private Set<Integer> userAccounts;
}
