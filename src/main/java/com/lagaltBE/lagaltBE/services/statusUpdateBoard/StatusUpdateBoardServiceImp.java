package com.lagaltBE.lagaltBE.services.statusUpdateBoard;

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
        return statusUpdateBoardRepository.findById(id).get();
    }

    @Override
    public Collection<StatusUpdateBoard> findAll() {
        return statusUpdateBoardRepository.findAll();
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
        statusUpdateBoardRepository.deleteById(id);
    }

    @Override
    public void delete(StatusUpdateBoard entity) {
        statusUpdateBoardRepository.delete(entity);
    }
}
