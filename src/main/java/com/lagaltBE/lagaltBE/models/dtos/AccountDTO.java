package com.lagaltBE.lagaltBE.models.dtos;

import lombok.Data;
import java.util.Set;

@Data
public class AccountDTO {
    private int id;
    private String firstName;
    private String lastName;
    private String username;
    private String email;
    private boolean visible;
    private Set<Integer> contributors;
    private Set<Integer> skills;
}
