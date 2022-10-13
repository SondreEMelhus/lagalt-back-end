package com.lagaltBE.lagaltBE.mappers;

import com.lagaltBE.lagaltBE.models.Account;
import com.lagaltBE.lagaltBE.models.StatusUpdate;
import com.lagaltBE.lagaltBE.models.dtos.StatusUpdateDTO;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;

import java.util.Collection;

@Mapper(componentModel = "spring")
public abstract class StatusUpdateMapper {
    @Mapping(target = "username", source = "account", qualifiedByName = "accountToUsername")
    public abstract StatusUpdateDTO statusUpdateToStatusUpdateDto(StatusUpdate statusUpdate);

    public abstract Collection<StatusUpdateDTO> statusUpdateToStatusUpdateDto(Collection<StatusUpdate> statusUpdate);

    @Named("accountToUsername")
    String mapAccountToUsername(Account source) {
        return source.getUsername();
    }
}
