package com.lagaltBE.lagaltBE.models.dtos;

import lombok.Data;

@Data
public class ProjectInteractionHistoryDTO {
    private int id;
    private String timestamp;
    private Boolean visited;
    private int projectId;
}
