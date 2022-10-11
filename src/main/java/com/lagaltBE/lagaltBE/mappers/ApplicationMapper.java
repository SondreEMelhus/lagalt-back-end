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
    /*
    @Mapping(target = "firstName", source = "account", qualifiedByName = "applicationToFirstName")
    @Mapping(target = "lastName", source = "account", qualifiedByName = "applicationToLastName")
    public abstract ProjectApplicationDTO applicationToProjectApplicationDto(Application application);

    @Mapping(target = "project", source = "project", qualifiedByName = "applicationToProject")
    public abstract AccountApplicationDTO applicationToAccountApplicationDto(Application application);

    @Named("applicationToFirstName")
    String mapApplicationsToFirstName(Account source) {
        return source.getFirstName();
    }

    @Named("applicationToLastName")
    String mapApplicationsToLastName(Account source) {
        return source.getLastName();
    }

    @Named("applicationToProject")
    String mapApplicationsToProject(Project source) {
        return source.getTitle();
    }*/
}
