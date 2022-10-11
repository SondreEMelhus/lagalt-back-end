package com.lagaltBE.lagaltBE.services.application;

import com.lagaltBE.lagaltBE.models.Application;
import com.lagaltBE.lagaltBE.services.CrudService;
import org.springframework.stereotype.Service;

@Service
public interface ApplicationService extends CrudService<Application, Integer> {
}
