package com.lagaltBE.lagaltBE.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.NOT_FOUND)
public class EntityNotFoundException extends RuntimeException{
    public EntityNotFoundException(int id) {
        super("Entity does not exist with ID: " + id);
    }
}
