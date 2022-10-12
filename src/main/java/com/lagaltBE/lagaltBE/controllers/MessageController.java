package com.lagaltBE.lagaltBE.controllers;

import com.lagaltBE.lagaltBE.mappers.MessageMapper;
import com.lagaltBE.lagaltBE.models.Message;
import com.lagaltBE.lagaltBE.models.MessageBoard;
import com.lagaltBE.lagaltBE.models.dtos.AccountDTO;
import com.lagaltBE.lagaltBE.models.dtos.MessageDTO;
import com.lagaltBE.lagaltBE.services.message.MessageService;
import com.lagaltBE.lagaltBE.services.messageBoard.MessageBoardService;
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
@RequestMapping(path = "api/v1/messages")
public class MessageController {
    private final MessageMapper messageMapper;
    private final MessageService messageService;
    private final MessageBoardService messageBoardService;

    public MessageController(MessageMapper messageMapper, MessageService messageService, MessageBoardService messageBoardService) {
        this.messageMapper = messageMapper;
        this.messageService = messageService;
        this.messageBoardService = messageBoardService;
    }

    @Operation(summary = "Get a message by ID")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200",
                    description = "Success",
                    content = { @Content(mediaType = "application/json",
                            schema = @Schema(implementation = AccountDTO.class)) }),
            @ApiResponse(responseCode = "500",
                    description = "Message does not exist with supplied ID",
                    content = { @Content(mediaType = "application/json",
                            schema = @Schema(implementation = ErrorAttributeOptions.class)) })
    })
    @GetMapping("{id}")
    public ResponseEntity<MessageDTO> getById(@PathVariable int id) {
        MessageDTO messageDTO = messageMapper.messageToMessageDto(
                messageService.findById(id)
        );
        return ResponseEntity.ok(messageDTO);
    }

    @Operation(summary = "Get all messages in a message board")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200",
                    description = "Success",
                    content = {
                            @Content(
                                    mediaType = "application/json",
                                    array = @ArraySchema(schema = @Schema(implementation = AccountDTO.class))) })
    })
    @GetMapping("/messageBoard/{id}")
    public ResponseEntity getAllMessagesInMessageBoard(@PathVariable int id) {
        MessageBoard messageBoard = messageBoardService.findById(id);
        Set<Message> messages = messageBoard.getMessages();
        return ResponseEntity.ok(messages.stream().map(messageMapper::messageToMessageDto));
    }

    @Operation(summary = "Updates a message")
    @ApiResponses( value = {
            @ApiResponse(responseCode = "204",
                    description = "Message successfully updated",
                    content = @Content),
            @ApiResponse(responseCode = "400",
                    description = "Malformed request",
                    content = { @Content(mediaType = "application/json",
                            schema = @Schema(implementation = ErrorAttributeOptions.class)) }),
            @ApiResponse(responseCode = "404",
                    description = "Message not found with supplied ID",
                    content = @Content)
    })
    @PutMapping("{id}")
    public ResponseEntity update(@RequestBody Message message, @PathVariable int id) {
        if (id != message.getId())
            return ResponseEntity.badRequest().build();
        messageService.update(message);
        return ResponseEntity.noContent().build();
    }

    @Operation(summary = "Add a message")
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
    public  ResponseEntity add(@RequestBody Message message) {
        Message newMessage = messageService.add(message);
        URI location = URI.create("messages/" + newMessage.getId());
        return ResponseEntity.created(location).build();
    }

}
