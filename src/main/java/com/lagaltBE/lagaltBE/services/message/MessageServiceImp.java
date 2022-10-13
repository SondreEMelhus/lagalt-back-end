package com.lagaltBE.lagaltBE.services.message;

import com.lagaltBE.lagaltBE.models.Message;
import com.lagaltBE.lagaltBE.repositories.MessageRepository;
import org.springframework.stereotype.Service;

import java.util.Collection;

@Service
public class MessageServiceImp implements MessageService {

    private final MessageRepository messageRepository;

    public MessageServiceImp(MessageRepository messageRepository) {
        this.messageRepository = messageRepository;
    }

    @Override
    public Message findById(Integer id) {
        return null;
    }

    @Override
    public Collection<Message> findAll() {
        return messageRepository.findAll();
    }

    @Override
    public Message add(Message entity) {
        return messageRepository.save(entity);
    }

    @Override
    public Message update(Message entity) {
        return null;
    }

    @Override
    public void deleteById(Integer id) {
    }

    @Override
    public void delete(Message entity) {
    }
}
