package com.lagaltBE.lagaltBE.models.dtos;

import lombok.Data;
import java.util.Set;

@Data
public class AccountDTO {
    private int id;
    private String name;
    private String email;
    private boolean visible;
    private Set<Integer> contributors;
    private Set<Integer> skills;
}
