package com.lagaltBE.lagaltBE.controllers;

import com.lagaltBE.lagaltBE.mappers.ChatMapper;
import com.lagaltBE.lagaltBE.models.Chat;
import com.lagaltBE.lagaltBE.models.Project;
import com.lagaltBE.lagaltBE.services.project.ProjectService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.springframework.boot.web.error.ErrorAttributeOptions;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.Set;

@RestController
@RequestMapping(path = "api/v1/chats")
public class ChatController {

    private final ChatMapper chatMapper;
    private final ProjectService projectService;

    public ChatController(ChatMapper chatMapper, ProjectService projectService) {
        this.chatMapper = chatMapper;
        this.projectService = projectService;
    }

    @Operation(summary = "Get all chats of a project")
    @ApiResponses( value = {
            @ApiResponse(responseCode = "200",
                    description = "success",
                    content = @Content),
            @ApiResponse(responseCode = "400",
                    description = "malformed request",
                    content = @Content),
            @ApiResponse(responseCode = "500",
                    description = "no such user",
                    content = { @Content(mediaType = "application/json",
                            schema = @Schema(implementation = ErrorAttributeOptions.class)) })
    })
    @GetMapping("/project/{id}")
    public ResponseEntity getAllChatsOfProject(@PathVariable int id){
        Project project = projectService.findById(id);
        Set<Chat> chats = project.getChats();
        return ResponseEntity.ok(chats.stream().map(chatMapper::chatToChatDto));
    }

    @Operation(summary = "Add a chat to a project")
    @ApiResponses( value = {
            @ApiResponse(responseCode = "201",
                    description = "success",
                    content = @Content),
            @ApiResponse(responseCode = "400",
                    description = "malformed request",
                    content = { @Content(mediaType = "application/json",
                            schema = @Schema(implementation = ErrorAttributeOptions.class)) })
    })
    @PostMapping("/{projectId}/addChat")
    public  ResponseEntity add(@PathVariable int projectId, @RequestBody Chat chat) {
        Project project = projectService.findById(projectId);
        Set<Chat> chats = project.getChats();
        chats.add(chat);
        project.setChats(chats);
        projectService.update(project);
        return ResponseEntity.noContent().build();
    }
}
