package com.lagaltBE.lagaltBE.controllers;

import com.lagaltBE.lagaltBE.mappers.ProjectInteractionHistoryMapper;
import com.lagaltBE.lagaltBE.models.Account;
import com.lagaltBE.lagaltBE.models.ProjectInteractionHistory;
import com.lagaltBE.lagaltBE.services.account.AccountService;
import com.lagaltBE.lagaltBE.services.projectInteractionHistory.ProjectInteractionHistoryService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.springframework.boot.web.error.ErrorAttributeOptions;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.net.URI;
import java.util.Set;

@RestController
@RequestMapping(path = "api/v1/projectInteractionHistories")
public class ProjectInteractionHistoryController {

    private final ProjectInteractionHistoryService projectInteractionHistoryService;
    private final AccountService accountService;
    private final ProjectInteractionHistoryMapper projectInteractionHistoryMapper;

    public ProjectInteractionHistoryController(ProjectInteractionHistoryService projectInteractionHistoryService, AccountService accountService, ProjectInteractionHistoryMapper projectInteractionHistoryMapper) {
        this.projectInteractionHistoryService = projectInteractionHistoryService;
        this.accountService = accountService;
        this.projectInteractionHistoryMapper = projectInteractionHistoryMapper;
    }

    @Operation(summary = "Get project interaction histories of a account")
    @ApiResponses( value = {
            @ApiResponse(responseCode = "200",
                    description = "success",
                    content = @Content),
            @ApiResponse(responseCode = "400",
                    description = "malformed request",
                    content = @Content),
            @ApiResponse(responseCode = "500",
                    description = "no such account",
                    content = { @Content(mediaType = "application/json",
                            schema = @Schema(implementation = ErrorAttributeOptions.class)) })
    })
    @GetMapping("/account/{accountId}")
    public ResponseEntity getProjectInteractionHistories(@PathVariable int accountId){
        Account account = accountService.findById(accountId);
        Set<ProjectInteractionHistory> projectInteractionHistories = account.getProjectInteractionHistory();
        return ResponseEntity.ok(projectInteractionHistories.stream().map(projectInteractionHistoryMapper::projectInteractionHistoryToProjectInteractionHistoryDTO));
    }

    @Operation(summary = "Adds a new project interaction history")
    @ApiResponses( value = {
            @ApiResponse(responseCode = "201",
                    description = "project interaction history successfully added",
                    content = @Content),
            @ApiResponse(responseCode = "400",
                    description = "Malformed request",
                    content = {@Content(mediaType = "application/json",
                            schema = @Schema(implementation = ErrorAttributeOptions.class))})
    })
    @PostMapping //POST: api/v1/projectInteractionHistories
    public ResponseEntity add(@RequestBody ProjectInteractionHistory projectInteractionHistory) {
        ProjectInteractionHistory newProjectInteractionHistory = projectInteractionHistoryService.add(projectInteractionHistory);
        URI location = URI.create("projectInteractionHistories/" + newProjectInteractionHistory.getId());
        return ResponseEntity.created(location).build();
    }

    @Operation(summary = "Updates a project interaction history")
    @ApiResponses( value = {
            @ApiResponse(responseCode = "204",
                    description = "Project interaction history successfully updated",
                    content = @Content),
            @ApiResponse(responseCode = "400",
                    description = "Malformed request",
                    content = { @Content(mediaType = "application/json",
                            schema = @Schema(implementation = ErrorAttributeOptions.class)) }),
            @ApiResponse(responseCode = "404",
                    description = "Project interaction history not found with supplied ID",
                    content = @Content)
    })
    @PutMapping("{id}") //PUT: api/v1/projectInteractionHistories
    public ResponseEntity update(@RequestBody ProjectInteractionHistory projectInteractionHistory, @PathVariable int id) {
        if (id != projectInteractionHistory.getId())
            return ResponseEntity.badRequest().build();
        projectInteractionHistoryService.update(projectInteractionHistory);
        return ResponseEntity.noContent().build();
    }
}
