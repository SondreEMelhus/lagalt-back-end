package com.lagaltBE.lagaltBE.services.industry;

import com.lagaltBE.lagaltBE.exceptions.EntityNotFoundException;
import com.lagaltBE.lagaltBE.models.Industry;
import com.lagaltBE.lagaltBE.repositories.IndustryRepository;
import org.springframework.stereotype.Service;
import java.util.Collection;

@Service
public class IndustryServiceImp implements IndustryService {
    private final IndustryRepository industryRepository;

    public IndustryServiceImp(IndustryRepository industryRepository) {
        this.industryRepository = industryRepository;
    }

    @Override
    public Industry findById(Integer id) {
        return industryRepository.findById(id).orElseThrow(() -> new EntityNotFoundException(id));
    }

    @Override
    public Collection<Industry> findAll() {
        return industryRepository.findAll();
    }

    @Override
    public Industry add(Industry entity) {
        return industryRepository.save(entity);
    }

    @Override
    public Industry update(Industry entity) {
        return null;
    }

    @Override
    public void deleteById(Integer id) {

    }

    @Override
    public void delete(Industry entity) {

    }
}
