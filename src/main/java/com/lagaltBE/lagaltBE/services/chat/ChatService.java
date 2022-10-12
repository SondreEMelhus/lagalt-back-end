package com.lagaltBE.lagaltBE.services.chat;

import com.lagaltBE.lagaltBE.models.Chat;
import com.lagaltBE.lagaltBE.services.CrudService;
import org.springframework.stereotype.Service;

@Service
public interface ChatService extends CrudService<Chat, Integer> {
}
