package com.lagaltBE.lagaltBE.mappers;

import com.lagaltBE.lagaltBE.models.Account;
import com.lagaltBE.lagaltBE.models.Chat;
import com.lagaltBE.lagaltBE.models.dtos.ChatDTO;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;

import java.util.Collection;
@Mapper(componentModel = "spring")
public abstract class ChatMapper {

    @Mapping(target = "username", source = "account", qualifiedByName = "accountToUsername")
    public abstract ChatDTO chatToChatDto(Chat chat);

    public abstract Collection<ChatDTO> chatToChatDto(Collection<Chat> chat);

    @Named("accountToUsername")
    String mapAccountToUsername(Account source) {
        return source.getUsername();
    }
}
