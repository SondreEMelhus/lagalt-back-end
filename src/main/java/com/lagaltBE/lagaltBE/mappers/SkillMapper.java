package com.lagaltBE.lagaltBE.mappers;

import com.lagaltBE.lagaltBE.models.Skill;
import com.lagaltBE.lagaltBE.models.UserAccount;
import com.lagaltBE.lagaltBE.models.dtos.SkillDTO;
import com.lagaltBE.lagaltBE.models.dtos.UserAccountDTO;
import com.lagaltBE.lagaltBE.services.skill.SkillService;
import com.lagaltBE.lagaltBE.services.userAccount.UserAccountService;
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
    protected UserAccountService userAccountService;

    @Mapping(target = "userAccounts", source = "userAccounts", qualifiedByName = "userAccountsToIds")
    public abstract SkillDTO skillToSkillDto(Skill skill);

    public abstract Collection<SkillDTO> skillToSkillDto(Collection<Skill> skills);

    @Mapping(target = "userAccounts", source = "userAccounts", qualifiedByName = "userAccountsIdsToUserAccounts")
    public abstract Skill skillDtoToSkill(SkillDTO dto);

    @Named("userAccountsIdsToUserAccounts")
    Set<UserAccount> mapIdsToUserAccounts(Set<Integer> id) {
        return id.stream()
                .map( i -> userAccountService.findById(i))
                .collect(Collectors.toSet());
    }

    @Named("userAccountsToIds")
    Set<Integer> mapUserAccountsToIds(Set<UserAccount> source) {
        if(source == null)
            return null;
        return source.stream()
                .map(s -> s.getId()).collect(Collectors.toSet());
    }
}
