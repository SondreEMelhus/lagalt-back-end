package com.lagaltBE.lagaltBE.mappers;

import com.lagaltBE.lagaltBE.models.Chat;
import com.lagaltBE.lagaltBE.models.dtos.ChatDTO;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public abstract class ChatMapper {

    public abstract ChatDTO chatToChatDto(Chat chat);
}
