package com.lagaltBE.lagaltBE.mappers;

import com.lagaltBE.lagaltBE.models.*;
import com.lagaltBE.lagaltBE.models.dtos.ProjectDTO;
import com.lagaltBE.lagaltBE.services.contributor.ContributorService;
import com.lagaltBE.lagaltBE.services.industry.IndustryService;
import com.lagaltBE.lagaltBE.services.skill.SkillService;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Collection;
import java.util.Set;
import java.util.stream.Collectors;

@Mapper(componentModel = "spring")
public abstract class ProjectMapper {

    @Autowired
    protected IndustryService industryService;

    @Mapping(target = "contributors", source = "contributors", qualifiedByName = "contributorsToString")
    @Mapping(target = "skills", source = "skills", qualifiedByName = "skillsToString")
    @Mapping(target = "industry", source = "industry", qualifiedByName = "industriesToString" )
    @Mapping(target = "keywords", source = "keywords", qualifiedByName = "keywordsToString" )
    public abstract ProjectDTO projectToProjectDto(Project project);

    public abstract Collection<ProjectDTO> projectToProjectDto(Collection<Project> project);

    @Named("contributorsToString")
    Set<String> mapContributorsToIds(Set<Contributor> contributors) {
        if (contributors == null) return null;
        return contributors.stream().map(contributor -> contributor.getAccount().getUsername()).collect(Collectors.toSet());
    }

    @Named("skillsToString")
    Set<String> mapSkillsToIds(Set<Skill> source) {
        if(source == null) return null;
        return source.stream().map(s -> s.getTitle()).collect(Collectors.toSet());
    }

    @Named("keywordsToString")
    Set<String> mapKeywordsToString(Set<Keyword> source) {
        if(source == null) return null;
        return source.stream().map(s -> s.getTitle()).collect(Collectors.toSet());
    }

    @Named("industryIdsToIndustry")
    Industry mapIndustryIdsToIndustries(Integer industryId) {
        return industryService.findById(industryId);
    }

    @Named("industriesToString")
    String mapIndustryToIds(Industry industry) {
        return industry.getTitle();
    }
}

