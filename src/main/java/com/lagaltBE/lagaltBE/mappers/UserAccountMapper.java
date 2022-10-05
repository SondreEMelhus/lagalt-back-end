package com.lagaltBE.lagaltBE.mappers;

import com.lagaltBE.lagaltBE.models.UserAccount;
import com.lagaltBE.lagaltBE.models.dtos.UserAccountDTO;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public abstract class UserAccountMapper {

    public abstract UserAccountDTO userAccountToUserAccountDto(UserAccount userAccount);

    @Mapping(target = "name")
    public abstract UserAccount userAccountDtoToUserAccount(UserAccountDTO userAccountDTO);
}
