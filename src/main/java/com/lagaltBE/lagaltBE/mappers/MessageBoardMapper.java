package com.lagaltBE.lagaltBE.mappers;

import com.lagaltBE.lagaltBE.models.MessageBoard;
import com.lagaltBE.lagaltBE.models.dtos.MessageBoardDTO;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public abstract class MessageBoardMapper {

    public abstract MessageBoardDTO messageBoardToMessageBoardDto(MessageBoard messageBoard);
}
