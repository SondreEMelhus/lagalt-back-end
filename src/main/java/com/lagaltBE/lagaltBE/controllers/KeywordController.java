package com.lagaltBE.lagaltBE.controllers;

import com.lagaltBE.lagaltBE.mappers.KeywordMapper;
import com.lagaltBE.lagaltBE.models.Industry;
import com.lagaltBE.lagaltBE.models.Keyword;
import com.lagaltBE.lagaltBE.models.dtos.AccountDTO;
import com.lagaltBE.lagaltBE.models.dtos.IndustryDTO;
import com.lagaltBE.lagaltBE.models.dtos.KeywordDTO;
import com.lagaltBE.lagaltBE.services.keyword.KeywordService;
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
@RequestMapping(path = "api/v1/keywords")
public class KeywordController {
    private final KeywordService keywordService;
    private final KeywordMapper keywordMapper;

    public KeywordController(KeywordService keywordService, KeywordMapper keywordMapper) {
        this.keywordService = keywordService;
        this.keywordMapper = keywordMapper;
    }

    @Operation(summary = "Get all keywords")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200",
                    description = "Success",
                    content = {
                            @Content(
                                    mediaType = "application/json",
                                    array = @ArraySchema(schema = @Schema(implementation = AccountDTO.class))) })
    })
    @GetMapping
    public ResponseEntity<Collection<KeywordDTO>> getAll() {
        Collection<KeywordDTO> keyword = keywordMapper.keywordToKeywordDto(
                keywordService.findAll()
        );
        return ResponseEntity.ok(keyword);
    }

    @Operation(summary = "Get a keyword by ID")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200",
                    description = "Success",
                    content = { @Content(mediaType = "application/json",
                            schema = @Schema(implementation = AccountDTO.class)) }),
            @ApiResponse(responseCode = "500",
                    description = "Keyword does not exist with supplied ID",
                    content = { @Content(mediaType = "application/json",
                            schema = @Schema(implementation = ErrorAttributeOptions.class)) })
    })
    @GetMapping("{id}")
    public ResponseEntity<KeywordDTO> getById(@PathVariable int id) {
        KeywordDTO keywordDTO = keywordMapper.keywordToKeywordDto(
                keywordService.findById(id)
        );
        return ResponseEntity.ok(keywordDTO);
    }

    @Operation(summary = "Add a keyword")
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
    public  ResponseEntity add(@RequestBody Keyword keyword) {
        Keyword newKeyword = keywordService.add(keyword);
        URI location = URI.create("industries/" + newKeyword.getId());
        return ResponseEntity.created(location).build();
    }

    @Operation(summary = "Updates a keyword")
    @ApiResponses( value = {
            @ApiResponse(responseCode = "204",
                    description = "Keyword successfully updated",
                    content = @Content),
            @ApiResponse(responseCode = "400",
                    description = "Malformed request",
                    content = { @Content(mediaType = "application/json",
                            schema = @Schema(implementation = ErrorAttributeOptions.class)) }),
            @ApiResponse(responseCode = "404",
                    description = "Keyword not found with supplied ID",
                    content = @Content)
    })
    @PutMapping("{id}")
    public ResponseEntity update(@RequestBody Keyword keyword, @PathVariable int id) {
        if (id != keyword.getId())
            return ResponseEntity.badRequest().build();
        keywordService.update(keyword);
        return ResponseEntity.noContent().build();
    }

    @Operation(summary = "Delete a keyword")
    @ApiResponses( value = {
            @ApiResponse(responseCode = "204",
                    description = "success",
                    content = @Content),
            @ApiResponse(responseCode = "500",
                    description = "no such keyword",
                    content = { @Content(mediaType = "application/json",
                            schema = @Schema(implementation = ErrorAttributeOptions.class)) })
    })
    @DeleteMapping("{id}")
    public ResponseEntity delete(@PathVariable int id) {
        keywordService.deleteById(id);
        return ResponseEntity.noContent().build();
    }
}
