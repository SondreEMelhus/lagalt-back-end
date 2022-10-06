package com.lagaltBE.lagaltBE.mappers;

import com.lagaltBE.lagaltBE.models.Skill;
import com.lagaltBE.lagaltBE.models.UserAccount;
import com.lagaltBE.lagaltBE.models.dtos.UserAccountDTO;
import com.lagaltBE.lagaltBE.services.skill.SkillService;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Collection;
import java.util.Set;
import java.util.stream.Collectors;

@Mapper(componentModel = "spring")
public abstract class UserAccountMapper {
    @Autowired
    protected SkillService skillService;

    @Mapping(target = "skills", source = "skills", qualifiedByName = "skillsToIds")
    public abstract UserAccountDTO userAccountToUserAccountDto(UserAccount userAccount);

    public abstract Collection<UserAccountDTO> userAccountToUserAccountDto(Collection<UserAccount> userAccounts);

    @Mapping(target = "skills", source = "skills", qualifiedByName = "skillIdsToSkills")
    public abstract UserAccount userAccountDtoToUserAccount(UserAccountDTO dto);

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
