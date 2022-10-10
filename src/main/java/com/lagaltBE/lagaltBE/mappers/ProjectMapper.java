package com.lagaltBE.lagaltBE.mappers;

import com.lagaltBE.lagaltBE.models.Contributor;
import com.lagaltBE.lagaltBE.models.Industry;
import com.lagaltBE.lagaltBE.models.Project;
import com.lagaltBE.lagaltBE.models.Skill;
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
    protected ContributorService contributorService;
    @Autowired
    protected SkillService skillService;
    @Autowired
    protected IndustryService industryService;

    @Mapping(target = "contributors", source = "contributors", qualifiedByName = "contributorsToIds")
    @Mapping(target = "skills", source = "skills", qualifiedByName = "skillsToIds")
    @Mapping(target = "industry", source = "industry", qualifiedByName = "industriesToIds" )
    public abstract ProjectDTO projectToProjectDto(Project project);

    public abstract Collection<ProjectDTO> projectToProjectDto(Collection<Project> project);

    @Mapping(target = "contributors", source = "contributors", qualifiedByName = "contributorsIdsToContributors")
    @Mapping(target = "skills", source = "skills", qualifiedByName = "skillIdsToSkills")
    @Mapping(target = "industry", source = "industry", qualifiedByName = "industryIdsToIndustry") // try adding industry.id if it does not work, or remove industry.id on projectToProjectDto
    public abstract Project projectDtoToProject(ProjectDTO projectDTO);

    @Named("contributorsToIds")
    Set<Integer> mapContributorsToIds(Set<Contributor> contributors) {
        if (contributors == null) return null;
        return contributors.stream().map(Contributor::getId).collect(Collectors.toSet());
    }

    @Named("contributorsIdsToContributors")
    Set<Contributor> mapContributorsIdsToContributors(Set<Integer> contributorsIds) {
        if (contributorsIds == null) return null;
        return contributorsIds.stream().map(id -> contributorService.findById(id)).collect(Collectors.toSet());
    }
    @Named("industryIdsToIndustry")
    Industry mapIndustryIdsToIndustries(Integer industryId) {
        return industryService.findById(industryId);
    }

    @Named("industriesToIds")
    Integer mapIndustryToIds(Industry industry) {
        return industry.getId();
    }

    @Named("skillIdsToSkills")
    Set<Skill> mapIdsToSkills(Set<Integer> id) {
        return id.stream()
                .map( i -> skillService.findById(i))
                .collect(Collectors.toSet());
    }

    @Named("skillsToIds")
    Set<Integer> mapSkillsToIds(Set<Skill> source) {
        if(source == null)
            return null;
        return source.stream()
                .map(s -> s.getId()).collect(Collectors.toSet());
    }
}
