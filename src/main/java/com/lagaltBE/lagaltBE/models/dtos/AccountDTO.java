package com.lagaltBE.lagaltBE.models.dtos;

import lombok.Data;
import java.util.Set;

@Data
public class AccountDTO {
    private int id;
    private String username;
    private String description;
    private boolean visible;
    private Set<String> skills;
}
