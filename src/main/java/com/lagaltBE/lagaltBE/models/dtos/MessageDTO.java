package com.lagaltBE.lagaltBE.models.dtos;

import lombok.Data;

@Data
public class MessageDTO {
    private int id;
    private String text;
    private String timestamp;
    private String username;
}
