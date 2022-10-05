package com.lagaltBE.lagaltBE.services.contributor;

import com.lagaltBE.lagaltBE.models.Contributor;
import com.lagaltBE.lagaltBE.services.CrudService;
import org.springframework.stereotype.Service;

@Service
public interface ContributorService extends CrudService<Contributor, Integer> {
}
