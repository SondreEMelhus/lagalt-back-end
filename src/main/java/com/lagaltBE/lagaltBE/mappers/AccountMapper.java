package com.lagaltBE.lagaltBE.mappers;

import com.lagaltBE.lagaltBE.models.Account;
import com.lagaltBE.lagaltBE.models.Contributor;
import com.lagaltBE.lagaltBE.models.dtos.AccountDTO;
import com.lagaltBE.lagaltBE.services.contributor.ContributorService;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Collection;
import java.util.Set;
import java.util.stream.Collectors;

@Mapper(componentModel = "spring")
public abstract class AccountMapper {

    @Autowired
    protected ContributorService contributorService;

    @Mapping(target = "contributors", source = "contributors", qualifiedByName = "contributorsToIds")
    public abstract AccountDTO accountToAccountDto(Account account);

    public abstract Collection<AccountDTO> accountToAccountDto(Collection<Account> accounts);

    @Mapping(target = "contributors", source = "contributors", qualifiedByName = "contributorsIdsToContributors")
    public abstract Account accountDtoToAccount(AccountDTO accountDTO);

    @Named("contributorsToIds")
    Set<Integer> mapContributorsToIds(Set<Contributor> contributors) {
        if (contributors == null) return null;
        return contributors.stream().map(contributor -> contributor.getId()).collect(Collectors.toSet());
    }

    @Named("contributorsIdsToContributors")
    Set<Contributor> mapContributorsIdsToContributors(Set<Integer> contributorsIds) {
        if (contributorsIds == null) return null;
        return contributorsIds.stream().map(id -> contributorService.findById(id)).collect(Collectors.toSet());
    }

}
