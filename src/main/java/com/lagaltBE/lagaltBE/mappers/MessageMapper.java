package com.lagaltBE.lagaltBE.mappers;

import com.lagaltBE.lagaltBE.models.Account;
import com.lagaltBE.lagaltBE.models.Message;
import com.lagaltBE.lagaltBE.models.dtos.MessageDTO;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;

import java.util.Collection;

@Mapper(componentModel = "spring")
public abstract class MessageMapper {

    @Mapping(target = "username", source = "account", qualifiedByName = "accountToUserName")
    public abstract MessageDTO messageToMessageDto(Message message);

    public abstract Collection<MessageDTO> messageToMessageDto(Collection<Message> message);

    @Named("accountToUserName")
    String mapAccountToUserName(Account source) {
        return source.getUsername();
    }
}
