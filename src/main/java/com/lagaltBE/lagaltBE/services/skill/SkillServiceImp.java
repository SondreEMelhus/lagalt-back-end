package com.lagaltBE.lagaltBE.services.skill;

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
        // TODO add or else throw UserAccountNotFoundException like this: or find another solution
        // return studentRepository.findById(id).orElseThrow(() -> new StudentNotFoundException(id));
        return skillRepository.findById(id).get();
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
        return skillRepository.save(entity);
    }

    @Override
    public void deleteById(Integer id) {
        skillRepository.deleteById(id);
    }

    @Override
    public void delete(Skill entity) {
        skillRepository.delete(entity);
    }
}
