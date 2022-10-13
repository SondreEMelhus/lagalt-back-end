package com.lagaltBE.lagaltBE.mappers;

import com.lagaltBE.lagaltBE.models.Account;
import com.lagaltBE.lagaltBE.models.Application;
import com.lagaltBE.lagaltBE.models.Project;
import com.lagaltBE.lagaltBE.models.dtos.AccountApplicationDTO;
import com.lagaltBE.lagaltBE.models.dtos.ProjectApplicationDTO;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;

@Mapper(componentModel = "spring")
public abstract class ApplicationMapper {
    @Mapping(target = "username", source = "account", qualifiedByName = "accountToUsername")
    public abstract ProjectApplicationDTO applicationToProjectApplicationDto(Application application);

    @Mapping(target = "projectTitle", source = "project", qualifiedByName = "projectToProjectTitle")
    public abstract AccountApplicationDTO applicationToAccountApplicationDto(Application application);

    @Named("accountToUsername")
    String mapApplicationsToUsername(Account source) {
        return source.getUsername();
    }

    @Named("projectToProjectTitle")
    String mapProjectToProjectTitle(Project source) {
        return source.getTitle();
    }
}
