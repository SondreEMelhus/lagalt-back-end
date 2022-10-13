package com.lagaltBE.lagaltBE.models.dtos;

import lombok.Data;
import java.util.Set;

@Data
public class KeywordDTO {
    private int id;
    private String title;
    private Set<String> industries;
}
