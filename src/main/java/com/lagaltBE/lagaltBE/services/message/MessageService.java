package com.lagaltBE.lagaltBE.services.message;

import com.lagaltBE.lagaltBE.models.Message;
import com.lagaltBE.lagaltBE.services.CrudService;
import org.springframework.stereotype.Service;

@Service
public interface MessageService extends CrudService<Message, Integer> {
}
