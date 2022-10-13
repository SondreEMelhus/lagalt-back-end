package com.lagaltBE.lagaltBE.mappers;

import com.lagaltBE.lagaltBE.models.Industry;
import com.lagaltBE.lagaltBE.models.Keyword;
import com.lagaltBE.lagaltBE.models.dtos.KeywordDTO;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;
import java.util.Collection;
import java.util.Set;
import java.util.stream.Collectors;

@Mapper(componentModel = "spring")
public abstract class KeywordMapper {

    @Mapping(target = "industries", source = "industries", qualifiedByName = "industriesToString")
    public abstract KeywordDTO keywordToKeywordDto(Keyword keyword);

    public abstract Collection<KeywordDTO> keywordToKeywordDto(Collection<Keyword> keywords);

    @Named("industriesToString")
    Set<String> mapIndustriesToString(Set<Industry> source) {
        if(source == null) return null;
        return source.stream().map(s -> s.getTitle()).collect(Collectors.toSet());
    }
}
