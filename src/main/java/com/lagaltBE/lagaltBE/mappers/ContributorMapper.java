package com.lagaltBE.lagaltBE.mappers;

import com.lagaltBE.lagaltBE.models.Account;
import com.lagaltBE.lagaltBE.models.Contributor;
import com.lagaltBE.lagaltBE.models.Industry;
import com.lagaltBE.lagaltBE.models.dtos.ContributorDTO;
import com.lagaltBE.lagaltBE.models.dtos.IndustryDTO;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;

import java.util.Collection;

@Mapper(componentModel = "spring")
public abstract class ContributorMapper {

    @Mapping(target = "account", source = "account", qualifiedByName = "accountToString")
    public abstract ContributorDTO contributorToContributorDto(Contributor contributor);

    public abstract Collection<ContributorDTO> contributorToContributorDto(Collection<Contributor> contributors);

    @Named("accountToString")
    String mapAccountToString(Account source) {
        return source.getUsername();
    }
}
