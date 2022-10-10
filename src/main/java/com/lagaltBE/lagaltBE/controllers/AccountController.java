package com.lagaltBE.lagaltBE.controllers;

import com.lagaltBE.lagaltBE.mappers.AccountMapper;
import com.lagaltBE.lagaltBE.models.Account;
import com.lagaltBE.lagaltBE.models.dtos.AccountDTO;
import com.lagaltBE.lagaltBE.mappers.SkillMapper;
import com.lagaltBE.lagaltBE.models.Skill;
import com.lagaltBE.lagaltBE.services.skill.SkillService;
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
import java.util.Set;

@RestController
@RequestMapping(path = "api/v1/accounts")
public class AccountController {

    private final AccountService accountService;
    private final AccountMapper accountMapper;
    private final SkillMapper skillMapper;
    private final SkillService skillService;

    public AccountController(AccountService accountService, AccountMapper accountMapper, SkillMapper skillMapper, SkillService skillService) {
        this.accountService = accountService;
        this.accountMapper = accountMapper;
        this.skillMapper = skillMapper;
        this.skillService = skillService;
    }

    @Operation(summary = "Get all user accounts")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200",
                    description = "Success",
                    content = { @Content(
                            mediaType = "application/json",
                            array = @ArraySchema(schema = @Schema(implementation = AccountDTO.class))) }),
            @ApiResponse(responseCode = "404",
                    description = "Users does not exist with supplied ID",
                    content = {
                            @Content(
                                    mediaType = "application/json",
                                    schema = @Schema(implementation = ErrorAttributeOptions.class)) })
    })
    @GetMapping //GET: api/v1/accounts
    public ResponseEntity getAll() {
        Collection<AccountDTO> accounts = accountMapper.accountToAccountDto(
                accountService.findAll()
        );
        return ResponseEntity.ok(accounts);
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
    @GetMapping("{id}") //GET: api/v1/accounts/1
    public ResponseEntity getById(@PathVariable int id) {
        AccountDTO account = accountMapper.accountToAccountDto(
                accountService.findById(id)
        );
        return ResponseEntity.ok(account);
    }

    @Operation(summary = "Add an account")
    @ApiResponses( value = {
            @ApiResponse(responseCode = "201",
                    description = "success",
                    content = @Content),
            @ApiResponse(responseCode = "400",
                    description = "malformed request",
                    content = { @Content(mediaType = "application/json",
                            schema = @Schema(implementation = ErrorAttributeOptions.class)) })
    })
    @PostMapping    //POST: api/v1/accounts
    public  ResponseEntity add(@RequestBody Account account) {
        Account user = accountService.add(account);
        URI location = URI.create("accounts/" + user.getId());
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
    @PutMapping("{id}") //PUT: api/v1/accounts
    public ResponseEntity update(@RequestBody Account account, @PathVariable int id) {
        if (id != account.getId())
            return ResponseEntity.badRequest().build();
        accountService.update(account);
        return ResponseEntity.noContent().build();
    }

    @Operation(summary = "Delete a user account")
    @ApiResponses( value = {
            @ApiResponse(responseCode = "204",
                    description = "success",
                    content = @Content),
            @ApiResponse(responseCode = "500",
                    description = "no such user",
                    content = { @Content(mediaType = "application/json",
                            schema = @Schema(implementation = ErrorAttributeOptions.class)) })
    })
    @DeleteMapping("{id}")  //DELETE: api/v1/accounts/1
    public ResponseEntity delete(@PathVariable int id) {
        accountService.deleteById(id);
        return ResponseEntity.noContent().build();
    }

    // endre navn til getSkills og path til //GET: api/v1/accounts/1/skills ???
    @Operation(summary = "Get skills of a user")
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
    @GetMapping("/getUserSkills/{id}")
    public ResponseEntity getUserSkills(@PathVariable int id){
        Account user = accountService.findById(id);
        Set<Skill> skills = user.getSkills();
        return ResponseEntity.ok(skills.stream().map(skillMapper::skillToSkillDto));
    }

    // endre navn til addSkill og path til //POST: api/v1/accounts/1/skills ???
    @Operation(summary = "Adds a skill to a user")
    @ApiResponses( value = {
            @ApiResponse(responseCode = "204",
                    description = "Skill successfully added",
                    content = @Content),
            @ApiResponse(responseCode = "400",
                    description = "Malformed request",
                    content = { @Content(mediaType = "application/json",
                            schema = @Schema(implementation = ErrorAttributeOptions.class)) }),
            @ApiResponse(responseCode = "404",
                    description = "User not found with supplied ID",
                    content = @Content)
    })
    @PutMapping("/addSkillToUser/{userId}")
    public ResponseEntity addSkill(@PathVariable int userId, @RequestBody int skillId) {
        Account user = accountService.findById(userId);
        Skill skill = skillService.findById(skillId);
        Set<Skill> skills = user.getSkills();
        skills.add(skill);
        user.setSkills(skills);
        accountService.update(user);
        return ResponseEntity.noContent().build();
    }

    // endre navn til removeSkill og path til //DELETE: api/v1/accounts/1/skills ???
    @Operation(summary = "Removes a skill from a user")
    @ApiResponses( value = {
            @ApiResponse(responseCode = "204",
                    description = "Skill successfully removed",
                    content = @Content),
            @ApiResponse(responseCode = "400",
                    description = "Malformed request",
                    content = { @Content(mediaType = "application/json",
                            schema = @Schema(implementation = ErrorAttributeOptions.class)) }),
            @ApiResponse(responseCode = "404",
                    description = "User not found with supplied ID",
                    content = @Content)
    })
    @PutMapping("/removeSkillFromUser/{userId}")
    public ResponseEntity removeSkill(@PathVariable int userId, @RequestBody int skillId) {
        Account user = accountService.findById(userId);
        Skill skill = skillService.findById(skillId);
        Set<Skill> skills = user.getSkills();
        skills.remove(skill);
        user.setSkills(skills);
        accountService.update(user);
        return ResponseEntity.noContent().build();
    }

    @Operation(summary = "Set profile to visible")
    @ApiResponses( value = {
            @ApiResponse(responseCode = "204",
                    description = "Profile successfully set to visible",
                    content = @Content),
            @ApiResponse(responseCode = "400",
                    description = "Malformed request",
                    content = { @Content(mediaType = "application/json",
                            schema = @Schema(implementation = ErrorAttributeOptions.class)) }),
            @ApiResponse(responseCode = "404",
                    description = "User not found with supplied ID",
                    content = @Content)
    })
    @PutMapping("/setProfileToVisible/{id}")
    public ResponseEntity setProfileToVisible(@PathVariable int id) {
        Account account = accountService.findById(id);
        account.setVisible(true);
        accountService.update(account);
        return ResponseEntity.noContent().build();
    }

    @Operation(summary = "Set profile to hidden")
    @ApiResponses( value = {
            @ApiResponse(responseCode = "204",
                    description = "Profile successfully set to hidden",
                    content = @Content),
            @ApiResponse(responseCode = "400",
                    description = "Malformed request",
                    content = { @Content(mediaType = "application/json",
                            schema = @Schema(implementation = ErrorAttributeOptions.class)) }),
            @ApiResponse(responseCode = "404",
                    description = "User not found with supplied ID",
                    content = @Content)
    })
    @PutMapping("/setProfileToHidden/{id}")
    public ResponseEntity setProfileToHidden(@PathVariable int id) {
        Account account = accountService.findById(id);
        account.setVisible(false);
        accountService.update(account);
        return ResponseEntity.noContent().build();
    }
}
