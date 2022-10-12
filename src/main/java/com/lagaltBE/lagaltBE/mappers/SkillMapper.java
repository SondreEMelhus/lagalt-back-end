package com.lagaltBE.lagaltBE.mappers;

import com.lagaltBE.lagaltBE.models.Account;
import com.lagaltBE.lagaltBE.models.Skill;
import com.lagaltBE.lagaltBE.models.dtos.SkillDTO;
import com.lagaltBE.lagaltBE.services.account.AccountService;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;
import org.springframework.beans.factory.annotation.Autowired;
import java.util.Collection;
import java.util.Set;
import java.util.stream.Collectors;

@Mapper(componentModel = "spring")
public abstract class SkillMapper {
    @Autowired
    protected AccountService accountService;

    @Mapping(target = "accounts", source = "accounts", qualifiedByName = "accountsToIds")
    public abstract SkillDTO skillToSkillDto(Skill skill);

    public abstract Collection<SkillDTO> skillToSkillDto(Collection<Skill> skills);

    @Mapping(target = "accounts", source = "accounts", qualifiedByName = "accountsIdsToAccounts")
    public abstract Skill skillDtoToSkill(SkillDTO dto);

    @Named("accountsIdsToAccounts")
    Set<Account> mapIdsToUserAccounts(Set<Integer> id) {
        return id.stream()
                .map( i -> accountService.findById(i))
                .collect(Collectors.toSet());
    }

    @Named("accountsToIds")
    Set<Integer> mapUserAccountsToIds(Set<Account> source) {
        if(source == null)
            return null;
        return source.stream()
                .map(s -> s.getId()).collect(Collectors.toSet());
    }
}
