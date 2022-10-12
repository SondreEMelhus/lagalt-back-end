package com.lagaltBE.lagaltBE.models.dtos;

import lombok.Data;

@Data
public class StatusUpdateDTO {
    private int id;
    private String text;
    private String timestamp;
    private String firstName;
    private String lastName;
}
