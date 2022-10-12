package com.lagaltBE.lagaltBE.services.projectInteractionHistory;

import com.lagaltBE.lagaltBE.models.ProjectInteractionHistory;
import com.lagaltBE.lagaltBE.repositories.ProjectInteractionHistoryRepository;
import org.springframework.stereotype.Service;
import java.util.Collection;

@Service
public class ProjectInteractionHistoryServiceImp implements ProjectInteractionHistoryService {

    private final ProjectInteractionHistoryRepository projectInteractionHistoryRepository;

    public ProjectInteractionHistoryServiceImp(ProjectInteractionHistoryRepository projectInteractionHistoryRepository) {
        this.projectInteractionHistoryRepository = projectInteractionHistoryRepository;
    }

    @Override
    public ProjectInteractionHistory findById(Integer id) {
        return projectInteractionHistoryRepository.findById(id).get();
    }

    @Override
    public Collection<ProjectInteractionHistory> findAll() {
        return projectInteractionHistoryRepository.findAll();
    }

    @Override
    public ProjectInteractionHistory add(ProjectInteractionHistory entity) {
        return projectInteractionHistoryRepository.save(entity);
    }

    @Override
    public ProjectInteractionHistory update(ProjectInteractionHistory entity) {
        return projectInteractionHistoryRepository.save(entity);
    }

    @Override
    public void deleteById(Integer id) {
        projectInteractionHistoryRepository.deleteById(id);
    }

    @Override
    public void delete(ProjectInteractionHistory entity) {
        projectInteractionHistoryRepository.delete(entity);
    }
}
