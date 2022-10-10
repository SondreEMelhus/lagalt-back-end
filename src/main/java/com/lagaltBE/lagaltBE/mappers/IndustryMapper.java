package com.lagaltBE.lagaltBE.mappers;

import com.lagaltBE.lagaltBE.models.Industry;
import com.lagaltBE.lagaltBE.models.Project;
import com.lagaltBE.lagaltBE.models.dtos.IndustryDTO;
import com.lagaltBE.lagaltBE.services.project.ProjectService;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Collection;
import java.util.Set;
import java.util.stream.Collectors;

@Mapper(componentModel = "spring")
public abstract class IndustryMapper {

    @Autowired
    protected ProjectService projectService;

    @Mapping(target = "projects", source = "projects", qualifiedByName = "projectsToIds")
    public abstract IndustryDTO industryToIndustryDto(Industry industry);

    public abstract Collection<IndustryDTO> industryToIndustryDto(Collection<Industry> industries);

    @Mapping(target = "projects", source = "projects", qualifiedByName = "projectsIdsToProjects")
    public abstract Industry industryDtoToIndustry(IndustryDTO dto);

    @Named("projectsToIds")
    Set<Integer> mapProjectsToIds(Set<Project> projects) {
        if (projects == null) return null;
        return projects.stream().map(project -> project.getId()).collect(Collectors.toSet());
    }

    @Named("projectsIdsToProjects")
    Set<Project> mapProjectsIdsToProjects(Set<Integer> projectsIds) {
        if (projectsIds == null) return null;
        return projectsIds.stream().map(id -> projectService.findById(id)).collect(Collectors.toSet());
    }
}
