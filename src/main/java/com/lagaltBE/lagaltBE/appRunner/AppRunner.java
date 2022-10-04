package com.lagaltBE.lagaltBE.appRunner;

import com.lagaltBE.lagaltBE.models.Project;
import com.lagaltBE.lagaltBE.repositories.ProjectRepository;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.stereotype.Component;

import java.util.Collection;

@Component
public class AppRunner implements ApplicationRunner {

    private final ProjectRepository projectRepository;

    public AppRunner(ProjectRepository projectRepository) {
        this.projectRepository = projectRepository;
    }

    @Override
    public void run(ApplicationArguments args) throws Exception {
        Collection<Project> projects =  projectRepository.findAll();
        System.out.println(projects.toString());
    }
}