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
    @Mapping(target = "firstName", source = "account", qualifiedByName = "accountToFirstName")
    @Mapping(target = "lastName", source = "account", qualifiedByName = "accountToLastName")
    public abstract StatusUpdateDTO statusUpdateToStatusUpdateDto(StatusUpdate statusUpdate);

    public abstract Collection<StatusUpdateDTO> statusUpdateToStatusUpdateDto(Collection<StatusUpdate> statusUpdate);

    @Named("accountToFirstName")
    String mapAccountToFirstName(Account source) {
        return source.getFirstName();
    }

    @Named("accountToLastName")
    String mapAccountToLastName(Account source) {
        return source.getLastName();
    }
}
