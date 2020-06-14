package com.survey.mole.service;

import com.survey.mole.exception.LevelReferenceException;
import com.survey.mole.model.survey.LevelReference;
import com.survey.mole.repository.LevelReferenceRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class LevelReferenceServiceImpl implements LevelReferenceService {

    private LevelReferenceRepository levelReferenceRepository;

    @Autowired
    public LevelReferenceServiceImpl(LevelReferenceRepository levelReferenceRepository) {
        this.levelReferenceRepository = levelReferenceRepository;
    }

    @Override
    public LevelReference save(LevelReference levelReference) {
        return levelReferenceRepository.saveAndFlush(levelReference);
    }

    @Override
    public LevelReference update(Long id, LevelReference levelReference) {
        LevelReference byId = findById(id);
        levelReference.setId(id);
        return levelReferenceRepository.save(levelReference);
    }

    @Override
    public LevelReference findById(Long id) {
        return levelReferenceRepository.findById(id)
                .orElseThrow(LevelReferenceException::new);
    }

    @Override
    public List<LevelReference> findAll() {
        return levelReferenceRepository.findAll();
    }

    @Override
    public Boolean delete(LevelReference levelReference) {
        long before = levelReferenceRepository.count();
        levelReferenceRepository.delete(levelReference);
        long after = levelReferenceRepository.count();
        return before > after;
    }
}
