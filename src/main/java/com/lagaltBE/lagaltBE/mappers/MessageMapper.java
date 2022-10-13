package com.lagaltBE.lagaltBE.mappers;

import com.lagaltBE.lagaltBE.models.Message;
import com.lagaltBE.lagaltBE.models.dtos.MessageDTO;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public abstract class MessageMapper {

    public abstract MessageDTO messageToMessageDto(Message message);
}
