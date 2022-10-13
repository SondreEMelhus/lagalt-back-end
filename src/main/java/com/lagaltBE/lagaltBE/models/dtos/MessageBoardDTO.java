package com.lagaltBE.lagaltBE.models.dtos;

import lombok.Data;

@Data
public class MessageBoardDTO {
    private int id;
    private String title;
    private String text;
    private String timestamp;
    private String username;
}
