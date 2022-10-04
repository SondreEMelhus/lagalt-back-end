package com.lagaltBE.lagaltBE.controllers;

import com.lagaltBE.lagaltBE.models.Project;
import com.lagaltBE.lagaltBE.services.ProjectService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Collection;

@RestController
@RequestMapping(path = "api/v1/projects")
public class ProjectController {

    private final ProjectService projectService;

    public ProjectController(ProjectService projectService) {
        this.projectService = projectService;
    }

    @GetMapping //GET: api/v1/projects
    public ResponseEntity<Collection<Project>> getAll() {
        System.out.println(projectService.findAll());
        return ResponseEntity.ok(projectService.findAll());
    }
}
