package com.lagaltBE.lagaltBE.services.skill;

import com.lagaltBE.lagaltBE.exceptions.EntityNotFoundException;
import com.lagaltBE.lagaltBE.models.Skill;
import com.lagaltBE.lagaltBE.repositories.SkillRepository;
import org.springframework.stereotype.Service;
import java.util.Collection;

@Service
public class SkillServiceImp implements SkillService {

    private final SkillRepository skillRepository;

    public SkillServiceImp(SkillRepository skillRepository) {
        this.skillRepository = skillRepository;
    }

    @Override
    public Skill findById(Integer id) {
        return skillRepository.findById(id).orElseThrow(() -> new EntityNotFoundException(id));
    }

    @Override
    public Collection<Skill> findAll() {
        return skillRepository.findAll();
    }

    @Override
    public Skill add(Skill entity) {
        return skillRepository.save(entity);
    }

    @Override
    public Skill update(Skill entity) {
        return null;
    }

    @Override
    public void deleteById(Integer id) {

    }

    @Override
    public void delete(Skill entity) {

    }
}
