package com.lagaltBE.lagaltBE.controllers;

import com.lagaltBE.lagaltBE.models.UserAccount;
import com.lagaltBE.lagaltBE.services.userAccount.UserAccountService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.net.URI;
import java.util.Collection;

@RestController
@RequestMapping(path = "api/v1/useraccounts")
public class UserAccountController {

    // TODO do we need find a user by name?
    private final UserAccountService userAccountService;

    public UserAccountController(UserAccountService userAccountService) {
        this.userAccountService = userAccountService;
    }

    @Operation(summary = "Get all users")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200",
                    description = "Success",
                    content = {
                            @Content(
                                    mediaType = "application/json",
                                    array = @ArraySchema(schema = @Schema(implementation = StudentDTO.class))) }),
            @ApiResponse(responseCode = "404",
                    description = "Users does not exist with supplied ID",
                    content = {
                            @Content(
                                    mediaType = "application/json",
                                    schema = @Schema(implementation = ApiErrorResponse.class)) })
    })
    @GetMapping
    public ResponseEntity<Collection<UserAccount>> getAll() {
        return ResponseEntity.ok(userAccountService.findAll());
    }

    @GetMapping("{id}")
    public ResponseEntity<UserAccount> getById(@PathVariable int id) {
        return ResponseEntity.ok(userAccountService.findById(id));
    }

    @PostMapping
    public  ResponseEntity add(@RequestBody UserAccount userAccount) {
        UserAccount user = userAccountService.add(userAccount);
        URI location = URI.create("useraccounts/" + user.getId());
        return ResponseEntity.created(location).build();
    }

    @PutMapping("{id}")
    public ResponseEntity update(@RequestBody UserAccount userAccount, @PathVariable int id) {
        if (id != userAccount.getId())
            return ResponseEntity.badRequest().build();
        userAccountService.update(userAccount);
        return ResponseEntity.noContent().build();
    }

    @DeleteMapping("{id}")
    public ResponseEntity delete(@PathVariable int id) {
        userAccountService.deleteById(id);
        return ResponseEntity.noContent().build();
    }
}
