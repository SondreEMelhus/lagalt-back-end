package com.lagaltBE.lagaltBE.services.messageBoard;

import com.lagaltBE.lagaltBE.models.MessageBoard;
import com.lagaltBE.lagaltBE.services.CrudService;
import org.springframework.stereotype.Service;

@Service
public interface MessageBoardService extends CrudService<MessageBoard, Integer> {
}
