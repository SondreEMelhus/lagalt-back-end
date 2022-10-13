package com.lagaltBE.lagaltBE.services.keyword;

import com.lagaltBE.lagaltBE.models.Keyword;
import com.lagaltBE.lagaltBE.repositories.KeywordRepository;
import org.springframework.stereotype.Service;
import java.util.Collection;

@Service
public class KeywordServiceImp implements KeywordService {

    private final KeywordRepository keywordRepository;

    public KeywordServiceImp(KeywordRepository keywordRepository) {
        this.keywordRepository = keywordRepository;
    }

    @Override
    public Keyword findById(Integer id) {
        return keywordRepository.findById(id).get();
    }

    @Override
    public Collection<Keyword> findAll() {
        return keywordRepository.findAll();
    }

    @Override
    public Keyword add(Keyword entity) {
        return keywordRepository.save(entity);
    }

    @Override
    public Keyword update(Keyword entity) {
        return null;
    }

    @Override
    public void deleteById(Integer id) {
    }

    @Override
    public void delete(Keyword entity) {
    }
}
