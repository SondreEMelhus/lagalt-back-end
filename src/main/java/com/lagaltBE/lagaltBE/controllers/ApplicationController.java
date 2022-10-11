package com.lagaltBE.lagaltBE.controllers;

import com.lagaltBE.lagaltBE.mappers.ApplicationMapper;
import com.lagaltBE.lagaltBE.models.*;
import com.lagaltBE.lagaltBE.models.dtos.AccountDTO;
import com.lagaltBE.lagaltBE.models.dtos.ProjectApplicationDTO;
import com.lagaltBE.lagaltBE.services.account.AccountService;
import com.lagaltBE.lagaltBE.services.application.ApplicationService;
import com.lagaltBE.lagaltBE.services.contributor.ContributorService;
import com.lagaltBE.lagaltBE.services.project.ProjectService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.springframework.boot.web.error.ErrorAttributeOptions;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.*;

@RestController
@RequestMapping(path = "api/v1/applications")
public class ApplicationController {
    /*
    private final ApplicationService applicationService;
    private final ApplicationMapper applicationMapper;
    private final ProjectService projectService;
    private final AccountService accountService;
    private final ContributorService contributorService;

    public ApplicationController(ApplicationService applicationService, ApplicationMapper applicationMapper, ProjectService projectService, AccountService accountService, ContributorService contributorService) {
        this.applicationService = applicationService;
        this.applicationMapper = applicationMapper;
        this.projectService = projectService;
        this.accountService = accountService;
        this.contributorService = contributorService;
    }

    @Operation(summary = "Get pending applications to a project by ID")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200",
                    description = "Success",
                    content = { @Content(mediaType = "application/json",
                            schema = @Schema(implementation = AccountDTO.class)) }),
            @ApiResponse(responseCode = "500",
                    description = "Project does not exist with supplied ID",
                    content = { @Content(mediaType = "application/json",
                            schema = @Schema(implementation = ErrorAttributeOptions.class)) })
    })
    @GetMapping("/project/{id}")
    public ResponseEntity<Collection<ProjectApplicationDTO>> getApplicationsToProject(@PathVariable int id) {
        Project project = projectService.findById(id);
        Set<Application> projectApplications = project.getApplications();
        Set<ProjectApplicationDTO> projectApplicationDTO = new HashSet<>();
        for (Application application : projectApplications){
            if (application.getStatus().compareTo("Pending") == 0) {
                projectApplicationDTO.add(applicationMapper.applicationToProjectApplicationDto(application));
            }
        }
        return ResponseEntity.ok(projectApplicationDTO);
    }

    @Operation(summary = "Get all applications from a user to projects by user ID")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200",
                    description = "Success",
                    content = { @Content(mediaType = "application/json",
                            schema = @Schema(implementation = AccountDTO.class)) }),
            @ApiResponse(responseCode = "500",
                    description = "User does not exist with supplied ID",
                    content = { @Content(mediaType = "application/json",
                            schema = @Schema(implementation = ErrorAttributeOptions.class)) })
    })
    @GetMapping("/account/{id}")
    public ResponseEntity getApplicationsByAccount(@PathVariable int id) {
        Account account = accountService.findById(id);
        Set<Application> applications = account.getApplications();
        return ResponseEntity.ok(applications.stream().map(applicationMapper::applicationToAccountApplicationDto));
    }

    @Operation(summary = "Accept an application")
    @ApiResponses( value = {
            @ApiResponse(responseCode = "204",
                    description = "Application successfully updated",
                    content = @Content),
            @ApiResponse(responseCode = "400",
                    description = "Malformed request",
                    content = { @Content(mediaType = "application/json",
                            schema = @Schema(implementation = ErrorAttributeOptions.class)) }),
            @ApiResponse(responseCode = "404",
                    description = "Application not found with supplied ID",
                    content = @Content)
    })
    @PutMapping("{id}/accept")
    public ResponseEntity accept(@PathVariable int id) {
        Application application = applicationService.findById(id);
        application.setStatus("Accepted");
        applicationService.update(application);
        // add the user as a contributor to the project
        Contributor contributor = new Contributor();
        contributor.setRole("member");
        contributor.setAccount(application.getAccount());
        contributor.setProject(application.getProject());
        contributorService.add(contributor);
        return ResponseEntity.noContent().build();
    }

    @Operation(summary = "Deny an application")
    @ApiResponses( value = {
            @ApiResponse(responseCode = "204",
                    description = "Application successfully updated",
                    content = @Content),
            @ApiResponse(responseCode = "400",
                    description = "Malformed request",
                    content = { @Content(mediaType = "application/json",
                            schema = @Schema(implementation = ErrorAttributeOptions.class)) }),
            @ApiResponse(responseCode = "404",
                    description = "Application not found with supplied ID",
                    content = @Content)
    })
    @PutMapping("{id}/deny")
    public ResponseEntity deny(@PathVariable int id) {
        Application application = applicationService.findById(id);
        application.setStatus("Denied");
        applicationService.update(application);
        return ResponseEntity.noContent().build();
    }*/
}
