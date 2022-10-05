package com.lagaltBE.lagaltBE.mappers;

import com.lagaltBE.lagaltBE.models.Project;
import com.lagaltBE.lagaltBE.models.dtos.ProjectDTO;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.Collection;

@Mapper(componentModel = "spring")
public abstract class ProjectMapper {

    public abstract ProjectDTO projectToProjectDto(Project project);

    public abstract Collection<ProjectDTO> projectToProjectDto(Collection<Project> project);

    public abstract Project projectDtoToProject(ProjectDTO projectDTO);
}
