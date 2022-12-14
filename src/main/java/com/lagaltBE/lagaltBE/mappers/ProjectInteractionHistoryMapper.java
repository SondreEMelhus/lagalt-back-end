package com.lagaltBE.lagaltBE.mappers;

import com.lagaltBE.lagaltBE.models.Project;
import com.lagaltBE.lagaltBE.models.ProjectInteractionHistory;
import com.lagaltBE.lagaltBE.models.dtos.ProjectInteractionHistoryDTO;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;

@Mapper(componentModel = "spring")
public abstract class ProjectInteractionHistoryMapper {

    @Mapping(target = "projectId", source = "project", qualifiedByName = "projectToId")
    public abstract ProjectInteractionHistoryDTO projectInteractionHistoryToProjectInteractionHistoryDTO(ProjectInteractionHistory projectInteractionHistory);

    @Named("projectToId")
    int mapProjectToId(Project source) {
        return source.getId();
    }
}
