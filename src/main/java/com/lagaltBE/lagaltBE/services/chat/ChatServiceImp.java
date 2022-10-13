package com.lagaltBE.lagaltBE.services.chat;

import com.lagaltBE.lagaltBE.models.Chat;
import com.lagaltBE.lagaltBE.repositories.ChatRepository;
import org.springframework.stereotype.Service;
import java.util.Collection;

@Service
public class ChatServiceImp implements ChatService {

    private final ChatRepository chatRepository;

    public ChatServiceImp(ChatRepository chatRepository) {
        this.chatRepository = chatRepository;
    }

    @Override
    public Chat findById(Integer id) {
        return null;
    }

    @Override
    public Collection<Chat> findAll() {
        return chatRepository.findAll();
    }

    @Override
    public Chat add(Chat entity) {
        return chatRepository.save(entity);
    }

    @Override
    public Chat update(Chat entity) {
        return null;
    }

    @Override
    public void deleteById(Integer id) {

    }

    @Override
    public void delete(Chat entity) {

    }
}
