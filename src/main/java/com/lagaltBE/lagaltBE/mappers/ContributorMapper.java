package com.lagaltBE.lagaltBE.mappers;

import com.lagaltBE.lagaltBE.models.Contributor;
import com.lagaltBE.lagaltBE.models.Project;
import com.lagaltBE.lagaltBE.models.dtos.ContributorDTO;
import com.lagaltBE.lagaltBE.models.dtos.ProjectDTO;
import org.mapstruct.Mapper;

import java.util.Collection;

@Mapper(componentModel = "spring")
public abstract class ContributorMapper {

    //public abstract Collection<ContributorDTO> contributorToContributorDto(Collection<Contributor> contributor);

}
