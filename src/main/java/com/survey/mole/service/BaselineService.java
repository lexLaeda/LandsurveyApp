package com.survey.mole.service;


import com.survey.mole.dto.BaselineDto;
import com.survey.mole.model.survey.Baseline;
import com.survey.mole.model.survey.Point;

import java.util.List;

public interface BaselineService {
    Baseline save(Baseline baseline);

    Baseline update(Long id, Baseline baseline);

    Baseline findById(Long id);

    List<Baseline> findAll();

    Boolean delete(Baseline baseline);

    List<Baseline> findByPoint(Point point);

    Boolean deleteList(List<Baseline> collect);
}
