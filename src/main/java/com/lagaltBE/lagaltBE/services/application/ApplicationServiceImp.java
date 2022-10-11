package com.lagaltBE.lagaltBE.services.application;

import com.lagaltBE.lagaltBE.models.Application;
import com.lagaltBE.lagaltBE.repositories.ApplicationRepository;
import org.springframework.stereotype.Service;
import java.util.Collection;

@Service
public class ApplicationServiceImp implements ApplicationService {

    private final ApplicationRepository applicationRepository;

    public ApplicationServiceImp(ApplicationRepository applicationRepository) {
        this.applicationRepository = applicationRepository;
    }

    @Override
    public Application findById(Integer id) {
        return applicationRepository.findById(id).get();
    }

    @Override
    public Collection<Application> findAll() {
        return applicationRepository.findAll();
    }

    @Override
    public Application add(Application entity) {
        return applicationRepository.save(entity);
    }

    @Override
    public Application update(Application entity) {
        return applicationRepository.save(entity);
    }

    @Override
    public void deleteById(Integer id) {
        applicationRepository.deleteById(id);
    }

    @Override
    public void delete(Application entity) {
        applicationRepository.delete(entity);
    }
}
