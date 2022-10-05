package com.lagaltBE.lagaltBE.services.project;

import com.lagaltBE.lagaltBE.models.Project;
import com.lagaltBE.lagaltBE.services.CrudService;
import org.springframework.stereotype.Service;

@Service
public interface ProjectService extends CrudService<Project, Integer> {
}
