package com.lagaltBE.lagaltBE.services.contributor;

import com.lagaltBE.lagaltBE.exceptions.EntityNotFoundException;
import com.lagaltBE.lagaltBE.models.Contributor;
import com.lagaltBE.lagaltBE.repositories.ContributorRepository;
import org.springframework.stereotype.Service;

import java.util.Collection;

@Service
public class ContributorServiceImp implements ContributorService {

    private final ContributorRepository contributorRepository;

    public ContributorServiceImp(ContributorRepository contributorRepository) {
        this.contributorRepository = contributorRepository;
    }

    @Override
    public Contributor findById(Integer id) {
        return contributorRepository.findById(id).orElseThrow(() -> new EntityNotFoundException(id));
    }

    @Override
    public Collection<Contributor> findAll() {
        return contributorRepository.findAll();
    }

    @Override
    public Contributor add(Contributor entity) {
        return contributorRepository.save(entity);
    }

    @Override
    public Contributor update(Contributor entity) {
        return contributorRepository.save(entity);
    }

    @Override
    public void deleteById(Integer integer) {

    }

    @Override
    public void delete(Contributor entity) {

    }
}

