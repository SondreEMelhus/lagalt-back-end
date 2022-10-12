package com.lagaltBE.lagaltBE.services.messageBoard;

import com.lagaltBE.lagaltBE.models.MessageBoard;
import com.lagaltBE.lagaltBE.repositories.MessageBoardRepository;
import org.springframework.stereotype.Service;

import java.util.Collection;

@Service
public class MessageBoardServiceImp implements MessageBoardService {

    private final MessageBoardRepository messageBoardRepository;

    public MessageBoardServiceImp(MessageBoardRepository messageBoardRepository) {
        this.messageBoardRepository = messageBoardRepository;
    }

    @Override
    public MessageBoard findById(Integer id) {
        return messageBoardRepository.findById(id).get();
    }

    @Override
    public Collection<MessageBoard> findAll() {
        return messageBoardRepository.findAll();
    }

    @Override
    public MessageBoard add(MessageBoard entity) {
        return messageBoardRepository.save(entity);
    }

    @Override
    public MessageBoard update(MessageBoard entity) {
        return messageBoardRepository.save(entity);
    }

    @Override
    public void deleteById(Integer id) {
        messageBoardRepository.deleteById(id);
    }

    @Override
    public void delete(MessageBoard entity) {
        messageBoardRepository.delete(entity);
    }
}
