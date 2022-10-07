package com.lagaltBE.lagaltBE.mappers;

import com.lagaltBE.lagaltBE.models.Account;
import com.lagaltBE.lagaltBE.models.Contributor;
import com.lagaltBE.lagaltBE.models.Skill;
import com.lagaltBE.lagaltBE.models.dtos.AccountDTO;
import com.lagaltBE.lagaltBE.services.contributor.ContributorService;
import com.lagaltBE.lagaltBE.services.skill.SkillService;
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
    @Autowired
    protected SkillService skillService;

    @Mapping(target = "contributors", source = "contributors", qualifiedByName = "contributorsToIds")
    @Mapping(target = "skills", source = "skills", qualifiedByName = "skillsToIds")
    public abstract AccountDTO accountToAccountDto(Account account);

    public abstract Collection<AccountDTO> accountToAccountDto(Collection<Account> accounts);

    @Mapping(target = "contributors", source = "contributors", qualifiedByName = "contributorsIdsToContributors")
    @Mapping(target = "skills", source = "skills", qualifiedByName = "skillIdsToSkills")
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
