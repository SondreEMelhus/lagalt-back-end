package com.lagaltBE.lagaltBE.services.statusUpdate;

import com.lagaltBE.lagaltBE.exceptions.EntityNotFoundException;
import com.lagaltBE.lagaltBE.models.StatusUpdate;
import com.lagaltBE.lagaltBE.repositories.StatusUpdateRepository;
import org.springframework.stereotype.Service;

import java.util.Collection;

@Service
public class StatusUpdateServiceImp implements StatusUpdateService{

    private final StatusUpdateRepository statusUpdateRepository;

    public StatusUpdateServiceImp(StatusUpdateRepository statusUpdateRepository) {
        this.statusUpdateRepository = statusUpdateRepository;
    }

    @Override
    public StatusUpdate findById(Integer id) {
        return statusUpdateRepository.findById(id).orElseThrow(() -> new EntityNotFoundException(id));
    }

    @Override
    public Collection<StatusUpdate> findAll() {
        return statusUpdateRepository.findAll();
    }

    @Override
    public StatusUpdate add(StatusUpdate entity) {
        return statusUpdateRepository.save(entity);
    }

    @Override
    public StatusUpdate update(StatusUpdate entity) {
        return null;
    }

    @Override
    public void deleteById(Integer id) {

    }

    @Override
    public void delete(StatusUpdate entity) {

    }
}
