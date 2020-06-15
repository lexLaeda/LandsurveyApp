package com.survey.mole.service;


import com.survey.mole.model.survey.Baseline;

import java.util.List;

public interface BaselineService {
    Baseline save(Baseline baseline);

    Baseline update(Long id, Baseline baseline);

    Baseline findById(Long id);

    List<Baseline> findAll();

    Boolean delete(Baseline baseline);

}
