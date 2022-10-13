package com.lagaltBE.lagaltBE.mappers;

import com.lagaltBE.lagaltBE.models.Industry;
import com.lagaltBE.lagaltBE.models.Keyword;
import com.lagaltBE.lagaltBE.models.Project;
import com.lagaltBE.lagaltBE.models.Skill;
import com.lagaltBE.lagaltBE.models.dtos.IndustryDTO;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;
import java.util.Collection;
import java.util.Set;
import java.util.stream.Collectors;

@Mapper(componentModel = "spring")
public abstract class IndustryMapper {

    @Mapping(target = "projects", source = "projects", qualifiedByName = "projectsToIds")
    @Mapping(target = "keywords", source = "keywords", qualifiedByName = "keywordsToString")
    @Mapping(target = "skills", source = "skills", qualifiedByName = "skillsToString")
    public abstract IndustryDTO industryToIndustryDto(Industry industry);

    public abstract Collection<IndustryDTO> industryToIndustryDto(Collection<Industry> industries);

    @Named("projectsToIds")
    Set<Integer> mapProjectsToIds(Set<Project> projects) {
        if (projects == null) return null;
        return projects.stream().map(project -> project.getId()).collect(Collectors.toSet());
    }

    @Named("keywordsToString")
    Set<String> mapKeywordsToString(Set<Keyword> source) {
        if(source == null) return null;
        return source.stream().map(s -> s.getTitle()).collect(Collectors.toSet());
    }

    @Named("skillsToString")
    Set<String> mapSkillsToString(Set<Skill> source) {
        if(source == null) return null;
        return source.stream().map(s -> s.getTitle()).collect(Collectors.toSet());
    }
}
