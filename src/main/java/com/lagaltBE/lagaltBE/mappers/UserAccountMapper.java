package com.lagaltBE.lagaltBE.mappers;

import com.lagaltBE.lagaltBE.models.Skill;
import com.lagaltBE.lagaltBE.models.UserAccount;
import com.lagaltBE.lagaltBE.models.dtos.UserAccountDTO;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;
import java.util.Set;
import java.util.stream.Collectors;

@Mapper(componentModel = "spring")
public abstract class UserAccountMapper {
    @Named("skillsToIds")
    Set<Integer> map(Set<Skill> source) {
        if(source == null)
            return null;
        return source.stream()
                .map(s -> s.getId()).collect(Collectors.toSet());
    }

    @Mapping(target = "skills", source = "skills", qualifiedByName = "skillsToIds")
    public abstract UserAccountDTO userAccountToUserAccountDto(UserAccount userAccount);

    @Mapping(target = "name", ignore = true)
    public abstract UserAccount userAccountDtoToUserAccount(UserAccountDTO userAccountDTO);
}
