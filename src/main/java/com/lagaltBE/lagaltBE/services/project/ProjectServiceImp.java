package com.lagaltBE.lagaltBE.services.project;

import com.lagaltBE.lagaltBE.models.Contributor;
import com.lagaltBE.lagaltBE.models.Project;
import com.lagaltBE.lagaltBE.repositories.ProjectRepository;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.Set;

@Service
public class ProjectServiceImp implements ProjectService {

    private final ProjectRepository projectRepository;

    public ProjectServiceImp(ProjectRepository projectRepository) {
        this.projectRepository = projectRepository;
    }

    @Override
    public Project findById(Integer integer) {
        return projectRepository.findById(integer).get();
    }

    @Override
    public Collection<Project> findAll() {
        return projectRepository.findAll();
    }

    @Override
    public Project add(Project entity) {
        return projectRepository.save(entity);
    }

    @Override
    public Project update(Project entity) {
        return projectRepository.save(entity);
    }

    @Override
    public void deleteById(Integer integer) {

    }

    @Override
    public void delete(Project entity) {

    }

    @Override
    public Collection<Project> findAllByName(String name) {
        return projectRepository.findAllByName(name);
    }

}
