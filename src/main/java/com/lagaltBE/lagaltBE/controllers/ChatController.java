package com.lagaltBE.lagaltBE.controllers;

import com.lagaltBE.lagaltBE.mappers.ChatMapper;
import com.lagaltBE.lagaltBE.models.Account;
import com.lagaltBE.lagaltBE.models.Chat;
import com.lagaltBE.lagaltBE.models.Project;
import com.lagaltBE.lagaltBE.models.dtos.AccountDTO;
import com.lagaltBE.lagaltBE.models.dtos.ChatDTO;
import com.lagaltBE.lagaltBE.services.chat.ChatService;
import com.lagaltBE.lagaltBE.services.project.ProjectService;
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
@RequestMapping(path = "api/v1/chats")
public class ChatController {

    private final ChatMapper chatMapper;
    private final ProjectService projectService;
    private final ChatService chatService;

    public ChatController(ChatMapper chatMapper, ProjectService projectService, ChatService chatService) {
        this.chatMapper = chatMapper;
        this.projectService = projectService;
        this.chatService = chatService;
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
    @PostMapping
    public  ResponseEntity add(@RequestBody Chat chat) {
        Chat newChat = chatService.add(chat);
        URI location = URI.create("chats/" + newChat.getId());
        return ResponseEntity.created(location).build();
    }
}
