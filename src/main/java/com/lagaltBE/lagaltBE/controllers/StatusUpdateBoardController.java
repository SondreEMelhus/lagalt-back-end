package com.lagaltBE.lagaltBE.controllers;

import com.lagaltBE.lagaltBE.mappers.StatusUpdateBoardMapper;
import com.lagaltBE.lagaltBE.models.Project;
import com.lagaltBE.lagaltBE.models.StatusUpdateBoard;
import com.lagaltBE.lagaltBE.models.dtos.AccountDTO;
import com.lagaltBE.lagaltBE.models.dtos.StatusUpdateBoardDTO;
import com.lagaltBE.lagaltBE.services.project.ProjectService;
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
@RequestMapping(path = "api/v1/statusUpdateBoards")
public class StatusUpdateBoardController {
    private final StatusUpdateBoardService statusUpdateBoardService;
    private final StatusUpdateBoardMapper statusUpdateBoardMapper;
    private final ProjectService projectService;

    public StatusUpdateBoardController(StatusUpdateBoardService statusUpdateBoardService, StatusUpdateBoardMapper statusUpdateBoardMapper, ProjectService projectService) {
        this.statusUpdateBoardService = statusUpdateBoardService;
        this.statusUpdateBoardMapper = statusUpdateBoardMapper;
        this.projectService = projectService;
    }

    @Operation(summary = "Get all status update boards of a project")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200",
                    description = "Success",
                    content = {
                            @Content(
                                    mediaType = "application/json",
                                    array = @ArraySchema(schema = @Schema(implementation = AccountDTO.class))) })
    })
    @GetMapping("/project/{id}")
    public ResponseEntity getAllStatusUpdateBoardsOfProject(@PathVariable int id) {
        Project project = projectService.findById(id);
        Set<StatusUpdateBoard> statusUpdateBoards = project.getStatusUpdateBoards();
        return ResponseEntity.ok(statusUpdateBoards.stream().map(statusUpdateBoardMapper::statusUpdateBoardToStatusUpdateBoardDto));
    }

    @Operation(summary = "Get a status update board by ID")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200",
                    description = "Success",
                    content = { @Content(mediaType = "application/json",
                            schema = @Schema(implementation = AccountDTO.class)) }),
            @ApiResponse(responseCode = "500",
                    description = "Status update board does not exist with supplied ID",
                    content = { @Content(mediaType = "application/json",
                            schema = @Schema(implementation = ErrorAttributeOptions.class)) })
    })
    @GetMapping("{id}")
    public ResponseEntity<StatusUpdateBoardDTO> getById(@PathVariable int id) {
        StatusUpdateBoardDTO statusUpdateBoardDTO = statusUpdateBoardMapper.statusUpdateBoardToStatusUpdateBoardDto(
                statusUpdateBoardService.findById(id)
        );
        return ResponseEntity.ok(statusUpdateBoardDTO);
    }

    @Operation(summary = "Updates a status update board")
    @ApiResponses( value = {
            @ApiResponse(responseCode = "204",
                    description = "Status update board successfully updated",
                    content = @Content),
            @ApiResponse(responseCode = "400",
                    description = "Malformed request",
                    content = { @Content(mediaType = "application/json",
                            schema = @Schema(implementation = ErrorAttributeOptions.class)) }),
            @ApiResponse(responseCode = "404",
                    description = "Status update board not found with supplied ID",
                    content = @Content)
    })
    @PutMapping("{id}")
    public ResponseEntity update(@RequestBody StatusUpdateBoard statusUpdateBoard, @PathVariable int id) {
        if (id != statusUpdateBoard.getId())
            return ResponseEntity.badRequest().build();
        statusUpdateBoardService.update(statusUpdateBoard);
        return ResponseEntity.noContent().build();
    }

    @Operation(summary = "Add a status update board")
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
    public ResponseEntity add(@RequestBody StatusUpdateBoard statusUpdateBoard) {
        StatusUpdateBoard newStatusUpdateBoard = statusUpdateBoardService.add(statusUpdateBoard);
        URI location = URI.create("statusUpdateBoards/" + newStatusUpdateBoard.getId());
        return ResponseEntity.created(location).build();
    }
}
