package com.lagaltBE.lagaltBE.controllers;

import com.lagaltBE.lagaltBE.models.Skill;
import com.lagaltBE.lagaltBE.models.dtos.SkillDTO;
import com.lagaltBE.lagaltBE.services.skill.SkillService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.springframework.boot.web.error.ErrorAttributeOptions;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.net.URI;
import java.util.Collection;

@RestController
@RequestMapping(path = "api/v1/skills")
public class SkillController {

    private final SkillService skillService;

    public SkillController(SkillService skillService) {
        this.skillService = skillService;
    }

    @Operation(summary = "Get all skills")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200",
                    description = "Success",
                    content = {
                            @Content(
                                    mediaType = "application/json",
                                    array = @ArraySchema(schema = @Schema(implementation = SkillDTO.class))) }),
            @ApiResponse(responseCode = "404",
                    description = "Skill does not exist with supplied ID",
                    content = {
                            @Content(
                                    mediaType = "application/json",
                                    schema = @Schema(implementation = ErrorAttributeOptions.class)) })
    })
    @GetMapping
    public ResponseEntity<Collection<Skill>> getAll() {
        return ResponseEntity.ok(skillService.findAll());
    }

    @Operation(summary = "Get a skill by ID")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200",
                    description = "Success",
                    content = { @Content(mediaType = "application/json",
                            schema = @Schema(implementation = SkillDTO.class)) }),
            @ApiResponse(responseCode = "500",
                    description = "Skill does not exist with supplied ID",
                    content = { @Content(mediaType = "application/json",
                            schema = @Schema(implementation = ErrorAttributeOptions.class)) })
    })
    @GetMapping("{id}")
    public ResponseEntity<Skill> getById(@PathVariable int id) {
        return ResponseEntity.ok(skillService.findById(id));
    }

    @Operation(summary = "Add a skill")
    @ApiResponses( value = {
            @ApiResponse(responseCode = "201",
                    description = "success",
                    content = @Content),
            @ApiResponse(responseCode = "400",
                    description = "malformed request",
                    content = { @Content(mediaType = "application/json",
                            schema = @Schema(implementation = ErrorAttributeOptions.class)) })
    })
    @PostMapping
    public  ResponseEntity add(@RequestBody Skill skill) {
        Skill newSkill = skillService.add(skill);
        URI location = URI.create("skills/" + newSkill.getId());
        return ResponseEntity.created(location).build();
    }

    @Operation(summary = "Updates a skill")
    @ApiResponses( value = {
            @ApiResponse(responseCode = "204",
                    description = "Skill successfully updated",
                    content = @Content),
            @ApiResponse(responseCode = "400",
                    description = "Malformed request",
                    content = { @Content(mediaType = "application/json",
                            schema = @Schema(implementation = ErrorAttributeOptions.class)) }),
            @ApiResponse(responseCode = "404",
                    description = "Skill not found with supplied ID",
                    content = @Content)
    })
    @PutMapping("{id}")
    public ResponseEntity update(@RequestBody Skill skill, @PathVariable int id) {
        if (id != skill.getId())
            return ResponseEntity.badRequest().build();
        skillService.update(skill);
        return ResponseEntity.noContent().build();
    }

    @Operation(summary = "Delete a skill")
    @ApiResponses( value = {
            @ApiResponse(responseCode = "204",
                    description = "success",
                    content = @Content),
            @ApiResponse(responseCode = "500",
                    description = "no such skill",
                    content = { @Content(mediaType = "application/json",
                            schema = @Schema(implementation = ErrorAttributeOptions.class)) })
    })
    @DeleteMapping("{id}")
    public ResponseEntity delete(@PathVariable int id) {
        skillService.deleteById(id);
        return ResponseEntity.noContent().build();
    }
}
