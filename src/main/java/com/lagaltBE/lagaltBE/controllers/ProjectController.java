package com.lagaltBE.lagaltBE.controllers;

import com.lagaltBE.lagaltBE.mappers.AccountMapper;
import com.lagaltBE.lagaltBE.mappers.ContributorMapper;
import com.lagaltBE.lagaltBE.mappers.ProjectMapper;
import com.lagaltBE.lagaltBE.mappers.SkillMapper;
import com.lagaltBE.lagaltBE.models.Account;
import com.lagaltBE.lagaltBE.models.Project;
import com.lagaltBE.lagaltBE.models.Skill;
import com.lagaltBE.lagaltBE.models.dtos.AccountDTO;
import com.lagaltBE.lagaltBE.models.dtos.ContributorDTO;
import com.lagaltBE.lagaltBE.models.dtos.ProjectDTO;
import com.lagaltBE.lagaltBE.services.project.ProjectService;
import com.lagaltBE.lagaltBE.services.skill.SkillService;
import com.lagaltBE.lagaltBE.util.ApiErrorResponse;
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
@CrossOrigin("*")
@RestController
@RequestMapping(path = "api/v1/projects")
public class ProjectController {

    private final ProjectService projectService;
    private final ProjectMapper projectMapper;
    private final ContributorMapper contributorMapper;
    private final AccountMapper accountMapper;
    private final SkillMapper skillMapper;
    private final SkillService skillService;

    public ProjectController(ProjectService projectService, ProjectMapper projectMapper, ContributorMapper contributorMapper, AccountMapper accountMapper, SkillMapper skillMapper, SkillService skillService) {
        this.projectService = projectService;
        this.projectMapper = projectMapper;
        this.contributorMapper = contributorMapper;
        this.accountMapper = accountMapper;
        this.skillMapper = skillMapper;
        this.skillService = skillService;
    }

    @Operation(summary = "Get all projects")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200",
                description = "Success",
                content = {@Content(mediaType = "application/json",
                array = @ArraySchema(schema = @Schema(implementation = ProjectDTO.class))) }),
        @ApiResponse(responseCode = "404",
                description = "Project does not exist with supplied ID",
                content = {@Content(mediaType = "application/json",
                schema = @Schema(implementation = ApiErrorResponse.class)) })
    })
    @GetMapping //GET: api/v1/projects
    public ResponseEntity getAll() {
        Collection<ProjectDTO> projects = projectMapper.projectToProjectDto(
                projectService.findAll()
        );
        return ResponseEntity.ok(projects);
    }

    @Operation(summary = "Get a project by ID")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200",
                    description = "Success",
                    content = {@Content(mediaType = "application/json",
                    schema = @Schema(implementation = ProjectDTO.class))}),
            @ApiResponse(responseCode = "404",
                    description = "Project does not exist with supplied ID",
                    content = {@Content(mediaType = "application/json",
                    schema = @Schema(implementation = ApiErrorResponse.class))})
    })

    @GetMapping("{id}") //GET: api/v1/projects/1
    public ResponseEntity getById(@PathVariable int id) {
        ProjectDTO projectDTO = projectMapper.projectToProjectDto(
                projectService.findById(id)
        );
        return ResponseEntity.ok(projectDTO);
    }

    @GetMapping("{id}/contributors") //GET: api/v1/projects/1
    public ResponseEntity getContributors(@PathVariable int id) {
        Collection<ContributorDTO> contributors = contributorMapper.contributorToContributorDto(
                projectService.findById(id).getContributors()
        );
        return ResponseEntity.ok(contributors);
    }

    @GetMapping("{id}/accounts") //GET: api/v1/projects/1
    public ResponseEntity getAccounts(@PathVariable int id) {
        Collection<AccountDTO> accounts = accountMapper.accountToAccountDto(
                projectService.findById(id).getAccounts()
        );
        return ResponseEntity.ok(accounts);
    }

    @Operation(summary = "Adds a new project")
    @ApiResponses( value = {
            @ApiResponse(responseCode = "201",
                    description = "project successfully added",
                    content = @Content),
            @ApiResponse(responseCode = "400",
                    description = "Malformed request",
                    content = {@Content(mediaType = "application/json",
                    schema = @Schema(implementation = ErrorAttributeOptions.class))})
    })
    @PostMapping //POST: api/v1/projects
    public ResponseEntity add(@RequestBody Project project) {
        Project p = projectService.add(project);
        URI location = URI.create("projects/" + p.getId());
        return ResponseEntity.created(location).build();
    }

    @CrossOrigin("*")
    @Operation(summary = "Updates a project")
    @ApiResponses( value = {
            @ApiResponse(responseCode = "204",
                    description = "Project successfully updated",
                    content = @Content),
            @ApiResponse(responseCode = "400",
                    description = "Malformed request",
                    content = { @Content(mediaType = "application/json",
                    schema = @Schema(implementation = ErrorAttributeOptions.class)) }),
            @ApiResponse(responseCode = "404",
                    description = "Project not found with supplied ID",
                    content = @Content)
    })
    @PutMapping("{id}")  //POST: api/v1/projects/1
    public ResponseEntity update(@RequestBody ProjectDTO projectDTO, @PathVariable int id) {
        if ( id != projectDTO.getId() ) {
            return ResponseEntity.badRequest().build();
        }
        projectService.update(
                projectMapper.projectDtoToProject(projectDTO)
        );
        return ResponseEntity.noContent().build();
    }

    @CrossOrigin("*")
    @Operation(summary = "Deletes a project")
    @ApiResponses( value = {
            @ApiResponse(responseCode = "201",
                    description = "success",
                    content = @Content),
            @ApiResponse(responseCode = "500",
                    description = "no such project",
                    content = { @Content(mediaType = "application/json",
                    schema = @Schema(implementation = ErrorAttributeOptions.class)) })
    })
    @DeleteMapping("{id}")  //POST: api/v1/projects/1
    public ResponseEntity delete(@PathVariable int id) {
        projectService.deleteById(id);
        return ResponseEntity.noContent().build();
    }

    @Operation(summary = "Get skills of a project")
    @ApiResponses( value = {
            @ApiResponse(responseCode = "200",
                    description = "success",
                    content = @Content),
            @ApiResponse(responseCode = "400",
                    description = "malformed request",
                    content = @Content),
            @ApiResponse(responseCode = "500",
                    description = "no such project",
                    content = { @Content(mediaType = "application/json",
                            schema = @Schema(implementation = ErrorAttributeOptions.class)) })
    })
    @GetMapping("/getProjectSkills/{id}")
    public ResponseEntity getProjectSkills(@PathVariable int id){
        Project project = projectService.findById(id);
        Set<Skill> skills = project.getSkills();
        return ResponseEntity.ok(skills.stream().map(skillMapper::skillToSkillDto));
    }

    @Operation(summary = "Add a skill to a project")
    @ApiResponses( value = {
            @ApiResponse(responseCode = "204",
                    description = "Skill successfully added",
                    content = @Content),
            @ApiResponse(responseCode = "400",
                    description = "Malformed request",
                    content = { @Content(mediaType = "application/json",
                            schema = @Schema(implementation = ErrorAttributeOptions.class)) }),
            @ApiResponse(responseCode = "404",
                    description = "Project not found with supplied ID",
                    content = @Content)
    })
    @PutMapping("/addSkillToProject/{projectId}")
    public ResponseEntity addSkill(@PathVariable int projectId, @RequestBody int skillId) {
        Project project = projectService.findById(projectId);
        Skill skill = skillService.findById(skillId);
        Set<Skill> skills = project.getSkills();
        skills.add(skill);
        project.setSkills(skills);
        projectService.update(project);
        return ResponseEntity.noContent().build();
    }

    @Operation(summary = "Removes a skill from a project")
    @ApiResponses( value = {
            @ApiResponse(responseCode = "204",
                    description = "Skill successfully removed",
                    content = @Content),
            @ApiResponse(responseCode = "400",
                    description = "Malformed request",
                    content = { @Content(mediaType = "application/json",
                            schema = @Schema(implementation = ErrorAttributeOptions.class)) }),
            @ApiResponse(responseCode = "404",
                    description = "Project not found with supplied ID",
                    content = @Content)
    })
    @PutMapping("/removeSkillFromProject/{projectId}")
    public ResponseEntity removeSkill(@PathVariable int projectId, @RequestBody int skillId) {
        Project project = projectService.findById(projectId);
        Skill skill = skillService.findById(skillId);
        Set<Skill> skills = project.getSkills();
        skills.remove(skill);
        project.setSkills(skills);
        projectService.update(project);
        return ResponseEntity.noContent().build();
    }
}
