package com.lagaltBE.lagaltBE.models.dtos;

import lombok.Data;
import java.util.Set;

@Data
public class AccountDTO {
    private int id;
    private String name;
    private String firstName;
    private String lastName;
    private String username;
    private String email;
    private String description;
    private boolean visible;
    //private Set<String> contributors;
    private Set<String> skills;
}
