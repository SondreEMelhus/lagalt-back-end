package com.lagaltBE.lagaltBE.services.statusUpdateBoard;

import com.lagaltBE.lagaltBE.exceptions.EntityNotFoundException;
import com.lagaltBE.lagaltBE.models.StatusUpdateBoard;
import com.lagaltBE.lagaltBE.repositories.StatusUpdateBoardRepository;
import org.springframework.stereotype.Service;

import java.util.Collection;

@Service
public class StatusUpdateBoardServiceImp implements StatusUpdateBoardService {

    private final StatusUpdateBoardRepository statusUpdateBoardRepository;

    public StatusUpdateBoardServiceImp(StatusUpdateBoardRepository statusUpdateBoardRepository) {
        this.statusUpdateBoardRepository = statusUpdateBoardRepository;
    }

    @Override
    public StatusUpdateBoard findById(Integer id) {
        return statusUpdateBoardRepository.findById(id).orElseThrow(() -> new EntityNotFoundException(id));
    }

    @Override
    public Collection<StatusUpdateBoard> findAll() {
        return null;
    }

    @Override
    public StatusUpdateBoard add(StatusUpdateBoard entity) {
        return statusUpdateBoardRepository.save(entity);
    }

    @Override
    public StatusUpdateBoard update(StatusUpdateBoard entity) {
        return statusUpdateBoardRepository.save(entity);
    }

    @Override
    public void deleteById(Integer id) {

    }

    @Override
    public void delete(StatusUpdateBoard entity) {

    }
}
