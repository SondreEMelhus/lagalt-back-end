package com.lagaltBE.lagaltBE.controllers;

import com.lagaltBE.lagaltBE.models.Account;
import com.lagaltBE.lagaltBE.models.dtos.AccountDTO;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import com.lagaltBE.lagaltBE.services.account.AccountService;
import org.springframework.boot.web.error.ErrorAttributeOptions;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.net.URI;
import java.util.Collection;

@RestController
@RequestMapping(path = "api/v1/useraccounts")
public class UserAccountController {

    // TODO do we need find a user by name?
    private final AccountService accountService;

    public UserAccountController(AccountService accountService) {
        this.accountService = accountService;
    }

    @Operation(summary = "Get all user accounts")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200",
                    description = "Success",
                    content = {
                            @Content(
                                    mediaType = "application/json",
                                    array = @ArraySchema(schema = @Schema(implementation = AccountDTO.class))) }),
            @ApiResponse(responseCode = "404",
                    description = "Users does not exist with supplied ID",
                    content = {
                            @Content(
                                    mediaType = "application/json",
                                    schema = @Schema(implementation = ErrorAttributeOptions.class)) })
    })
    @GetMapping
    public ResponseEntity<Collection<Account>> getAll() {
        return ResponseEntity.ok(accountService.findAll());
    }

    @Operation(summary = "Get a user account by ID")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200",
                    description = "Success",
                    content = { @Content(mediaType = "application/json",
                            schema = @Schema(implementation = AccountDTO.class)) }),
            @ApiResponse(responseCode = "500",
                    description = "User does not exist with supplied ID",
                    content = { @Content(mediaType = "application/json",
                            schema = @Schema(implementation = ErrorAttributeOptions.class)) })
    })
    @GetMapping("{id}")
    public ResponseEntity<Account> getById(@PathVariable int id) {
        return ResponseEntity.ok(accountService.findById(id));
    }

    @Operation(summary = "Add a user account")
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
    public  ResponseEntity add(@RequestBody Account account) {
        Account user = accountService.add(account);
        URI location = URI.create("useraccounts/" + user.getId());
        return ResponseEntity.created(location).build();
    }

    @Operation(summary = "Updates a user account")
    @ApiResponses( value = {
            @ApiResponse(responseCode = "204",
                    description = "User successfully updated",
                    content = @Content),
            @ApiResponse(responseCode = "400",
                    description = "Malformed request",
                    content = { @Content(mediaType = "application/json",
                            schema = @Schema(implementation = ErrorAttributeOptions.class)) }),
            @ApiResponse(responseCode = "404",
                    description = "User not found with supplied ID",
                    content = @Content)
    })
    @PutMapping("{id}")
    public ResponseEntity update(@RequestBody Account userAccount, @PathVariable int id) {
        if (id != userAccount.getId())
            return ResponseEntity.badRequest().build();
        accountService.update(userAccount);
        return ResponseEntity.noContent().build();
    }

    @Operation(summary = "Delete a user account")
    @ApiResponses( value = {
            @ApiResponse(responseCode = "201",
                    description = "success",
                    content = @Content),
            @ApiResponse(responseCode = "500",
                    description = "no such user",
                    content = { @Content(mediaType = "application/json",
                            schema = @Schema(implementation = ErrorAttributeOptions.class)) })
    })
    @DeleteMapping("{id}")
    public ResponseEntity delete(@PathVariable int id) {
        accountService.deleteById(id);
        return ResponseEntity.noContent().build();
    }
}
