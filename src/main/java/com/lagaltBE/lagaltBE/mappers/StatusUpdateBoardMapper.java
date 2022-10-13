package com.lagaltBE.lagaltBE.mappers;

import com.lagaltBE.lagaltBE.models.StatusUpdateBoard;
import com.lagaltBE.lagaltBE.models.dtos.StatusUpdateBoardDTO;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public abstract class StatusUpdateBoardMapper {

    public abstract StatusUpdateBoardDTO statusUpdateBoardToStatusUpdateBoardDto(StatusUpdateBoard statusUpdateBoard);

}
