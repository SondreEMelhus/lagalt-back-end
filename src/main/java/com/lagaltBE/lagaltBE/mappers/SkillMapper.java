package com.lagaltBE.lagaltBE.mappers;

import com.lagaltBE.lagaltBE.models.Skill;
import com.lagaltBE.lagaltBE.models.dtos.SkillDTO;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public abstract class SkillMapper {
    public abstract SkillDTO skillToSkillDto(Skill skill);

    @Mapping(target = "title", ignore = true)
    public abstract Skill skillDtoToUSkill(SkillDTO skillDTO);
}
