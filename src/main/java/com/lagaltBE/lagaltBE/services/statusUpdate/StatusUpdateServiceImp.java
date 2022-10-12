package com.lagaltBE.lagaltBE.services.statusUpdate;

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
        return statusUpdateRepository.findById(id).get();
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
        return statusUpdateRepository.save(entity);
    }

    @Override
    public void deleteById(Integer id) {
        statusUpdateRepository.deleteById(id);
    }

    @Override
    public void delete(StatusUpdate entity) {
        statusUpdateRepository.delete(entity);
    }
}
