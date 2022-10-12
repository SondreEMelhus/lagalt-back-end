package com.lagaltBE.lagaltBE.mappers;

import com.lagaltBE.lagaltBE.models.Account;
import com.lagaltBE.lagaltBE.models.MessageBoard;
import com.lagaltBE.lagaltBE.models.dtos.MessageBoardDTO;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;

import java.util.Collection;

@Mapper(componentModel = "spring")
public abstract class MessageBoardMapper {
    @Mapping(target = "firstName", source = "account", qualifiedByName = "accountToFirstName")
    @Mapping(target = "lastName", source = "account", qualifiedByName = "accountToLastName")
    public abstract MessageBoardDTO messageBoardToMessageBoardDto(MessageBoard messageBoard);

    public abstract Collection<MessageBoardDTO> messageBoardToMessageBoardDto(Collection<MessageBoard> messageBoard);

    @Named("accountToFirstName")
    String mapAccountToFirstName(Account source) {
        return source.getFirstName();
    }

    @Named("accountToLastName")
    String mapAccountToLastName(Account source) {
        return source.getLastName();
    }
}
