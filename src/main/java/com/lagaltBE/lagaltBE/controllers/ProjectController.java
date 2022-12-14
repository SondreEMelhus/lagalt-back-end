package com.lagaltBE.lagaltBE.controllers;

import com.lagaltBE.lagaltBE.mappers.ProjectMapper;
import com.lagaltBE.lagaltBE.mappers.SkillMapper;
import com.lagaltBE.lagaltBE.mappers.*;
import com.lagaltBE.lagaltBE.models.Contributor;
import com.lagaltBE.lagaltBE.models.Keyword;
import com.lagaltBE.lagaltBE.models.Project;
import com.lagaltBE.lagaltBE.models.Skill;
import com.lagaltBE.lagaltBE.models.dtos.ProjectDTO;
import com.lagaltBE.lagaltBE.services.contributor.ContributorService;
import com.lagaltBE.lagaltBE.services.keyword.KeywordService;
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

@RestController
@RequestMapping(path = "api/v1/projects")
public class ProjectController {

    private final ProjectService projectService;
    private final ProjectMapper projectMapper;
    private final SkillMapper skillMapper;
    private final SkillService skillService;
    private final IndustryMapper industryMapper;
    private final KeywordMapper keywordMapper;
    private final ContributorMapper contributorMapper;
    private final KeywordService keywordService;
    private final ContributorService contributorService;

    public ProjectController(ProjectService projectService, ProjectMapper projectMapper, SkillMapper skillMapper, SkillService skillService, IndustryMapper industryMapper, KeywordMapper keywordMapper, ContributorMapper contributorMapper, KeywordService keywordService, ContributorService contributorService) {
        this.projectService = projectService;
        this.projectMapper = projectMapper;
        this.skillMapper = skillMapper;
        this.skillService = skillService;
        this.industryMapper = industryMapper;
        this.keywordMapper = keywordMapper;
        this.contributorMapper = contributorMapper;
        this.keywordService = keywordService;
        this.contributorService = contributorService;
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

    @Operation(summary = "Get a project by name")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200",
                    description = "Success",
                    content = {@Content(mediaType = "application/json",
                            array = @ArraySchema(schema = @Schema(implementation = ProjectDTO.class)))}),
            @ApiResponse(responseCode = "404",
                    description = "project does not exist with supplied ID",
                    content = {@Content(mediaType = "application/json",
                            schema = @Schema(implementation = ApiErrorResponse.class))})
    })
    @GetMapping("search") // GET: localhost:8080/api/v1/characters/1
    public ResponseEntity<Collection<ProjectDTO>> findAllByName(@RequestParam String name){
        Collection<ProjectDTO> dtos = projectMapper.projectToProjectDto(
                projectService.findAllByName(name)
        );
        return ResponseEntity.ok(dtos);
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
    public ResponseEntity update(@RequestBody Project project, @PathVariable int id) {
        if ( id != project.getId() ) {
            return ResponseEntity.badRequest().build();
        }
        projectService.update(project);
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
    @GetMapping("/{id}/skills")
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
    @PutMapping("/{projectId}/addSkill")
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
    @PutMapping("/{projectId}/removeSkill")
    public ResponseEntity removeSkill(@PathVariable int projectId, @RequestBody int skillId) {
        Project project = projectService.findById(projectId);
        Skill skill = skillService.findById(skillId);
        Set<Skill> skills = project.getSkills();
        skills.remove(skill);
        project.setSkills(skills);
        projectService.update(project);
        return ResponseEntity.noContent().build();
    }

    @Operation(summary = "Get industry of a project")
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
    @GetMapping("/{id}/industry")
    public ResponseEntity getProjectIndustry(@PathVariable int id){
        Project project = projectService.findById(id);
        return ResponseEntity.ok(industryMapper.industryToIndustryDto(project.getIndustry()));
    }

    @Operation(summary = "Get keywords of a project")
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
    @GetMapping("/{id}/keywords")
    public ResponseEntity getProjectKeywords(@PathVariable int id){
        Project project = projectService.findById(id);
        return ResponseEntity.ok(keywordMapper.keywordToKeywordDto(project.getKeywords()));
    }

    @Operation(summary = "Add a keyword to a project")
    @ApiResponses( value = {
            @ApiResponse(responseCode = "204",
                    description = "Keyword successfully added",
                    content = @Content),
            @ApiResponse(responseCode = "400",
                    description = "Malformed request",
                    content = { @Content(mediaType = "application/json",
                            schema = @Schema(implementation = ErrorAttributeOptions.class)) }),
            @ApiResponse(responseCode = "404",
                    description = "Project not found with supplied ID",
                    content = @Content)
    })
    @PutMapping("/{projectId}/addKeyword")
    public ResponseEntity addKeyword(@PathVariable int projectId, @RequestBody int keywordId) {
        Project project = projectService.findById(projectId);
        Keyword keyword = keywordService.findById(keywordId);
        Set<Keyword> keywords = project.getKeywords();
        keywords.add(keyword);
        project.setKeywords(keywords);
        projectService.update(project);
        return ResponseEntity.noContent().build();
    }

    @Operation(summary = "Get contributors of a project")
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
    @GetMapping("/{id}/contributors")
    public ResponseEntity getProjectContributors(@PathVariable int id){
        Project project = projectService.findById(id);
        return ResponseEntity.ok(contributorMapper.contributorToContributorDto(project.getContributors()));
    }

    @Operation(summary = "Adds a new contributor")
    @ApiResponses( value = {
            @ApiResponse(responseCode = "201",
                    description = "contributor successfully added",
                    content = @Content),
            @ApiResponse(responseCode = "400",
                    description = "Malformed request",
                    content = {@Content(mediaType = "application/json",
                            schema = @Schema(implementation = ErrorAttributeOptions.class))})
    })
    @PostMapping ("/addContributor")
    public ResponseEntity addContributor(@RequestBody Contributor contributor) {
        Contributor newContributor = contributorService.add(contributor);
        URI location = URI.create("contributors/" + newContributor.getId());
        return ResponseEntity.created(location).build();
    }
}
