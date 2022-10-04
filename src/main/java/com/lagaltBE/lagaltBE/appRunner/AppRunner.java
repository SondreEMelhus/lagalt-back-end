package com.lagaltBE.lagaltBE.appRunner;

import com.lagaltBE.lagaltBE.models.Project;
import com.lagaltBE.lagaltBE.repositories.ProjectRepository;
import com.lagaltBE.lagaltBE.services.ProjectService;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

import java.util.Collection;

@Component
public class AppRunner implements ApplicationRunner {

    private final ProjectRepository projectRepository;
    private final ProjectService projectService;

    public AppRunner(ProjectRepository projectRepository, ProjectService projectService) {
        this.projectRepository = projectRepository;
        this.projectService = projectService;
    }

    @Override
    public void run(ApplicationArguments args) throws Exception {
        Collection<Project> projects =  projectService.findAll();
        for (Project p: projects) {
            System.out.println(p.toString());
        }
    }
}