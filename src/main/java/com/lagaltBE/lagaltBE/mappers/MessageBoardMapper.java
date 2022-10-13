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

    @Mapping(target = "username", source = "account", qualifiedByName = "accountToUsername")
    public abstract MessageBoardDTO messageBoardToMessageBoardDto(MessageBoard messageBoard);

    public abstract Collection<MessageBoardDTO> messageBoardToMessageBoardDto(Collection<MessageBoard> messageBoard);

    @Named("accountToUsername")
    String mapAccountToUsername(Account source) {
        return source.getUsername();
    }
}
