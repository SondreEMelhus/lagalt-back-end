package com.lagaltBE.lagaltBE.services;

import com.lagaltBE.lagaltBE.models.Project;
import com.lagaltBE.lagaltBE.repositories.ProjectRepository;

import java.util.Collection;

public class ProjectServiceImp implements ProjectService {
    private final ProjectRepository projectRepostiory;

    public ProjectServiceImp(ProjectRepository projectRepostiory) {
        this.projectRepostiory = projectRepostiory;
    }

    @Override
    public Project findById(Integer integer) {
        return projectRepostiory.findById(integer).get();
    }

    @Override
    public Collection<Project> findAll() {
        return projectRepostiory.findAll();
    }

    @Override
    public Project add(Project entity) {
        return projectRepostiory.save(entity);
    }

    @Override
    public Project update(Project entity) {
        return projectRepostiory.save(entity);
    }

    @Override
    public void deleteById(Integer integer) {
        projectRepostiory.deleteById(integer);
    }

    @Override
    public void delete(Project entity) {
        projectRepostiory.delete(entity);
    }
}
