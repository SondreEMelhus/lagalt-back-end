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
    protected SkillService skillService;

    @Mapping(target = "skills", source = "skills", qualifiedByName = "skillsToString")
    public abstract AccountDTO accountToAccountDto(Account account);

    public abstract Collection<AccountDTO> accountToAccountDto(Collection<Account> accounts);

    @Named("skillsToString")
    Set<String> mapSkillsToString(Set<Skill> source) {
        if(source == null) return null;
        return source.stream().map(s -> s.getTitle()).collect(Collectors.toSet());
    }
}
