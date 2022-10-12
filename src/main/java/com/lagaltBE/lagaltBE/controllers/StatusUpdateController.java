package com.lagaltBE.lagaltBE.controllers;

import com.lagaltBE.lagaltBE.mappers.StatusUpdateMapper;
import com.lagaltBE.lagaltBE.models.StatusUpdate;
import com.lagaltBE.lagaltBE.models.StatusUpdateBoard;
import com.lagaltBE.lagaltBE.models.dtos.AccountDTO;
import com.lagaltBE.lagaltBE.models.dtos.StatusUpdateDTO;
import com.lagaltBE.lagaltBE.services.statusUpdate.StatusUpdateService;
import com.lagaltBE.lagaltBE.services.statusUpdateBoard.StatusUpdateBoardService;
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
import java.util.Set;

@RestController
@RequestMapping(path = "api/v1/statusUpdates")
public class StatusUpdateController {
    private final StatusUpdateMapper statusUpdateMapper;
    private final StatusUpdateService statusUpdateService;
    private final StatusUpdateBoardService statusUpdateBoardService;

    public StatusUpdateController(StatusUpdateMapper statusUpdateMapper, StatusUpdateService statusUpdateService, StatusUpdateBoardService statusUpdateBoardService) {
        this.statusUpdateMapper = statusUpdateMapper;
        this.statusUpdateService = statusUpdateService;
        this.statusUpdateBoardService = statusUpdateBoardService;
    }


    @Operation(summary = "Get a status update by ID")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200",
                    description = "Success",
                    content = { @Content(mediaType = "application/json",
                            schema = @Schema(implementation = AccountDTO.class)) }),
            @ApiResponse(responseCode = "500",
                    description = "Status update does not exist with supplied ID",
                    content = { @Content(mediaType = "application/json",
                            schema = @Schema(implementation = ErrorAttributeOptions.class)) })
    })
    @GetMapping("{id}")
    public ResponseEntity<StatusUpdateDTO> getById(@PathVariable int id) {
        StatusUpdateDTO statusUpdateDTO = statusUpdateMapper.statusUpdateToStatusUpdateDto(
                statusUpdateService.findById(id)
        );
        return ResponseEntity.ok(statusUpdateDTO);
    }

    @Operation(summary = "Get all status updates in a status update board by id")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200",
                    description = "Success",
                    content = {
                            @Content(
                                    mediaType = "application/json",
                                    array = @ArraySchema(schema = @Schema(implementation = AccountDTO.class))) })
    })
    @GetMapping("/statusUpdateBoard/{id}")
    public ResponseEntity getAllStatusUpdatesInStatusUpdateBoard(@PathVariable int id) {
        StatusUpdateBoard statusUpdateBoard = statusUpdateBoardService.findById(id);
        Set<StatusUpdate> statusUpdates = statusUpdateBoard.getStatusUpdates();
        return ResponseEntity.ok(statusUpdates.stream().map(statusUpdateMapper::statusUpdateToStatusUpdateDto));
    }

    @Operation(summary = "Updates a status update")
    @ApiResponses( value = {
            @ApiResponse(responseCode = "204",
                    description = "Status update successfully updated",
                    content = @Content),
            @ApiResponse(responseCode = "400",
                    description = "Malformed request",
                    content = { @Content(mediaType = "application/json",
                            schema = @Schema(implementation = ErrorAttributeOptions.class)) }),
            @ApiResponse(responseCode = "404",
                    description = "Status update not found with supplied ID",
                    content = @Content)
    })
    @PutMapping("{id}")
    public ResponseEntity update(@RequestBody StatusUpdate statusUpdate, @PathVariable int id) {
        if (id != statusUpdate.getId())
            return ResponseEntity.badRequest().build();
        statusUpdateService.update(statusUpdate);
        return ResponseEntity.noContent().build();
    }

    @Operation(summary = "Add a status update")
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
    public  ResponseEntity add(@RequestBody StatusUpdate statusUpdate) {
        StatusUpdate newStatusUpdate = statusUpdateService.add(statusUpdate);
        URI location = URI.create("statusUpdates/" + newStatusUpdate.getId());
        return ResponseEntity.created(location).build();
    }
}
