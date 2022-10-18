package com.lagaltBE.lagaltBE.mappers;

import com.lagaltBE.lagaltBE.models.Account;
import com.lagaltBE.lagaltBE.models.Contributor;
import com.lagaltBE.lagaltBE.models.dtos.ContributorDTO;
import com.lagaltBE.lagaltBE.models.dtos.ContributorIdDTO;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;

import java.util.Collection;

@Mapper(componentModel = "spring")
public abstract class ContributorMapper {

    @Mapping(target = "username", source = "account", qualifiedByName = "accountToUsername")
    public abstract ContributorDTO contributorToContributorDto(Contributor contributor);

    public abstract Collection<ContributorDTO> contributorToContributorDto(Collection<Contributor> contributors);

    public abstract ContributorIdDTO contributorToContributorIdDto(Contributor contributor);

    public abstract Collection<ContributorIdDTO> contributorToContributorIdDto(Collection<Contributor> contributors);

    @Named("accountToUsername")
    String mapAccountToUsername(Account source) {
        return source.getUsername();
    }
}
