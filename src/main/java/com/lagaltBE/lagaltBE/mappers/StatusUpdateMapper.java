package com.lagaltBE.lagaltBE.mappers;

import com.lagaltBE.lagaltBE.models.StatusUpdate;
import com.lagaltBE.lagaltBE.models.dtos.StatusUpdateDTO;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public abstract class StatusUpdateMapper {
    public abstract StatusUpdateDTO statusUpdateToStatusUpdateDto(StatusUpdate statusUpdate);

}
