package com.lagaltBE.lagaltBE.mappers;

import com.lagaltBE.lagaltBE.models.Contributor;
import com.lagaltBE.lagaltBE.models.dtos.ContributorDTO;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.Collection;

@Mapper(componentModel = "spring")
public abstract class ContributorMapper {

    @Mapping(target = "project", source = "project.id")
    public abstract ContributorDTO contributorToContributorDto(Contributor contributor);

    public abstract Collection<ContributorDTO> contributorToContributorDto(Collection<Contributor> contributors);

}
