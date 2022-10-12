package com.lagaltBE.lagaltBE.mappers;

import com.lagaltBE.lagaltBE.models.Account;
import com.lagaltBE.lagaltBE.models.StatusUpdateBoard;
import com.lagaltBE.lagaltBE.models.dtos.StatusUpdateBoardDTO;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;

import java.util.Collection;

@Mapper(componentModel = "spring")
public abstract class StatusUpdateBoardMapper {
    @Mapping(target = "firstName", source = "account", qualifiedByName = "accountToFirstName")
    @Mapping(target = "lastName", source = "account", qualifiedByName = "accountToLastName")
    public abstract StatusUpdateBoardDTO statusUpdateBoardToStatusUpdateBoardDto(StatusUpdateBoard statusUpdateBoard);

    public abstract Collection<StatusUpdateBoardDTO> statusUpdateBoardToStatusUpdateBoardDto(Collection<StatusUpdateBoard> statusUpdateBoard);

    @Named("accountToFirstName")
    String mapAccountToFirstName(Account source) {
        return source.getFirstName();
    }

    @Named("accountToLastName")
    String mapAccountToLastName(Account source) {
        return source.getLastName();
    }
}
