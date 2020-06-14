package com.survey.mole.service;

import com.survey.mole.model.survey.LevelReference;

import java.util.List;

public interface LevelReferenceService {
    LevelReference save(LevelReference levelReference);

    LevelReference update(Long id, LevelReference levelReference);

    LevelReference findById(Long id);

    List<LevelReference> findAll();

    Boolean delete(LevelReference levelReference);
}
