package com.lagaltBE.lagaltBE.controllers;

import com.lagaltBE.lagaltBE.mappers.IndustryMapper;
import com.lagaltBE.lagaltBE.mappers.KeywordMapper;
import com.lagaltBE.lagaltBE.mappers.SkillMapper;
import com.lagaltBE.lagaltBE.models.Industry;
import com.lagaltBE.lagaltBE.models.dtos.AccountDTO;
import com.lagaltBE.lagaltBE.models.dtos.IndustryDTO;
import com.lagaltBE.lagaltBE.services.industry.IndustryService;
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
@RequestMapping(path = "api/v1/industries")
public class IndustryController {
    private final IndustryService industryService;
    private final IndustryMapper industryMapper;
    private final KeywordMapper keywordMapper;
    private final SkillMapper skillMapper;

    public IndustryController(IndustryService industryService, IndustryMapper industryMapper, KeywordMapper keywordMapper, SkillMapper skillMapper) {
        this.industryService = industryService;
        this.industryMapper = industryMapper;
        this.keywordMapper = keywordMapper;
        this.skillMapper = skillMapper;
    }

    @Operation(summary = "Get all industries")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200",
                    description = "Success",
                    content = {
                            @Content(
                                    mediaType = "application/json",
                                    array = @ArraySchema(schema = @Schema(implementation = AccountDTO.class))) })
    })
    @GetMapping
    public ResponseEntity<Collection<IndustryDTO>> getAll() {
        Collection<IndustryDTO> industry = industryMapper.industryToIndustryDto(
                industryService.findAll()
        );
        return ResponseEntity.ok(industry);
    }

    @Operation(summary = "Get a industry by ID")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200",
                    description = "Success",
                    content = { @Content(mediaType = "application/json",
                            schema = @Schema(implementation = AccountDTO.class)) }),
            @ApiResponse(responseCode = "500",
                    description = "Industry does not exist with supplied ID",
                    content = { @Content(mediaType = "application/json",
                            schema = @Schema(implementation = ErrorAttributeOptions.class)) })
    })
    @GetMapping("{id}")
    public ResponseEntity<IndustryDTO> getById(@PathVariable int id) {
        IndustryDTO industryDTO = industryMapper.industryToIndustryDto(
                industryService.findById(id)
        );
        return ResponseEntity.ok(industryDTO);
    }

    @Operation(summary = "Add a industry")
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
    public  ResponseEntity add(@RequestBody Industry industry) {
        Industry newIndustry = industryService.add(industry);
        URI location = URI.create("industries/" + newIndustry.getId());
        return ResponseEntity.created(location).build();
    }

    @Operation(summary = "Delete a industry")
    @ApiResponses( value = {
            @ApiResponse(responseCode = "204",
                    description = "success",
                    content = @Content),
            @ApiResponse(responseCode = "500",
                    description = "no such industry",
                    content = { @Content(mediaType = "application/json",
                            schema = @Schema(implementation = ErrorAttributeOptions.class)) })
    })
    @DeleteMapping("{id}")
    public ResponseEntity delete(@PathVariable int id) {
        industryService.deleteById(id);
        return ResponseEntity.noContent().build();
    }

    @Operation(summary = "Get keywords of a industry")
    @ApiResponses( value = {
            @ApiResponse(responseCode = "200",
                    description = "success",
                    content = @Content),
            @ApiResponse(responseCode = "400",
                    description = "malformed request",
                    content = @Content),
            @ApiResponse(responseCode = "500",
                    description = "no such industry",
                    content = { @Content(mediaType = "application/json",
                            schema = @Schema(implementation = ErrorAttributeOptions.class)) })
    })
    @GetMapping("/{id}/keywords")
    public ResponseEntity getIndustryKeywords(@PathVariable int id){
        Industry industry = industryService.findById(id);
        return ResponseEntity.ok(keywordMapper.keywordToKeywordDto(industry.getKeywords()));
    }

    @Operation(summary = "Get skills of a industry")
    @ApiResponses( value = {
            @ApiResponse(responseCode = "200",
                    description = "success",
                    content = @Content),
            @ApiResponse(responseCode = "400",
                    description = "malformed request",
                    content = @Content),
            @ApiResponse(responseCode = "500",
                    description = "no such industry",
                    content = { @Content(mediaType = "application/json",
                            schema = @Schema(implementation = ErrorAttributeOptions.class)) })
    })
    @GetMapping("/{id}/skill")
    public ResponseEntity getIndustrySkills(@PathVariable int id){
        Industry industry = industryService.findById(id);
        return ResponseEntity.ok(skillMapper.skillToSkillDto(industry.getSkills()));
    }
}
