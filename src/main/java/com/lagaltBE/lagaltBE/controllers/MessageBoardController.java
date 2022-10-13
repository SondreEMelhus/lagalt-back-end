package com.lagaltBE.lagaltBE.controllers;

import com.lagaltBE.lagaltBE.mappers.MessageBoardMapper;
import com.lagaltBE.lagaltBE.models.Keyword;
import com.lagaltBE.lagaltBE.models.MessageBoard;
import com.lagaltBE.lagaltBE.models.Project;
import com.lagaltBE.lagaltBE.models.Skill;
import com.lagaltBE.lagaltBE.models.dtos.AccountDTO;
import com.lagaltBE.lagaltBE.models.dtos.KeywordDTO;
import com.lagaltBE.lagaltBE.models.dtos.MessageBoardDTO;
import com.lagaltBE.lagaltBE.services.messageBoard.MessageBoardService;
import com.lagaltBE.lagaltBE.services.project.ProjectService;
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
import java.util.Set;

@RestController
@RequestMapping(path = "api/v1/messageBoards")
public class MessageBoardController {

    private final MessageBoardService messageBoardService;
    private final MessageBoardMapper messageBoardMapper;
    private final ProjectService projectService;

    public MessageBoardController(MessageBoardService messageBoardService, MessageBoardMapper messageBoardMapper, ProjectService projectService) {
        this.messageBoardService = messageBoardService;
        this.messageBoardMapper = messageBoardMapper;
        this.projectService = projectService;
    }

    @Operation(summary = "Get all message boards of a project")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200",
                    description = "Success",
                    content = {
                            @Content(
                                    mediaType = "application/json",
                                    array = @ArraySchema(schema = @Schema(implementation = AccountDTO.class))) })
    })
    @GetMapping("/project/{id}")
    public ResponseEntity getAllMessageBoardsOfProject(@PathVariable int id) {
        Project project = projectService.findById(id);
        Set<MessageBoard> messageBoards = project.getMessageBoards();
        return ResponseEntity.ok(messageBoards.stream().map(messageBoardMapper::messageBoardToMessageBoardDto));
    }

    @Operation(summary = "Get a message board by ID")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200",
                    description = "Success",
                    content = { @Content(mediaType = "application/json",
                            schema = @Schema(implementation = AccountDTO.class)) }),
            @ApiResponse(responseCode = "500",
                    description = "Message board does not exist with supplied ID",
                    content = { @Content(mediaType = "application/json",
                            schema = @Schema(implementation = ErrorAttributeOptions.class)) })
    })
    @GetMapping("{id}")
    public ResponseEntity<MessageBoardDTO> getById(@PathVariable int id) {
        MessageBoardDTO messageBoardDTO = messageBoardMapper.messageBoardToMessageBoardDto(
                messageBoardService.findById(id)
        );
        return ResponseEntity.ok(messageBoardDTO);
    }

    @Operation(summary = "Add a message board")
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
    public  ResponseEntity add(@RequestBody MessageBoard messageBoard) {
        MessageBoard newMessageBoard = messageBoardService.add(messageBoard);
        URI location = URI.create("messageBoards/" + newMessageBoard.getId());
        return ResponseEntity.created(location).build();
    }
}
