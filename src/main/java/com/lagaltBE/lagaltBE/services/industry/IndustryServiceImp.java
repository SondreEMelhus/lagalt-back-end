package com.lagaltBE.lagaltBE.services.industry;

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
        return industryRepository.findById(id).get();
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
        return industryRepository.save(entity);
    }

    @Override
    public void deleteById(Integer id) {
        industryRepository.deleteById(id);
    }

    @Override
    public void delete(Industry entity) {
        industryRepository.delete(entity);
    }
}
