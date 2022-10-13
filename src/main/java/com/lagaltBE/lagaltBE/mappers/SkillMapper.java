package com.lagaltBE.lagaltBE.mappers;

import com.lagaltBE.lagaltBE.models.Skill;
import com.lagaltBE.lagaltBE.models.dtos.SkillDTO;
import org.mapstruct.Mapper;
import java.util.Collection;

@Mapper(componentModel = "spring")
public abstract class SkillMapper {

    public abstract SkillDTO skillToSkillDto(Skill skill);

    public abstract Collection<SkillDTO> skillToSkillDto(Collection<Skill> skills);
}
