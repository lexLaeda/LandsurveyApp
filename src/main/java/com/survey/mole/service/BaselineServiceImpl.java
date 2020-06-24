package com.survey.mole.service;

import com.survey.mole.exception.BaselineNotFoundException;
import com.survey.mole.exception.ElementNotFoundException;
import com.survey.mole.model.survey.Baseline;
import com.survey.mole.model.survey.Point;
import com.survey.mole.repository.BaselineRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BaselineServiceImpl implements BaselineService {


    private BaselineRepository baselineRepository;

    @Autowired
    public BaselineServiceImpl(BaselineRepository baselineRepository) {
        this.baselineRepository = baselineRepository;
    }

    @Override
    public Baseline save(Baseline baseline) {
        return baselineRepository.saveAndFlush(baseline);
    }

    @Override
    public Baseline update(Long id, Baseline baseline) {
        Baseline byId = findById(id);
        baseline.setId(id);
        Baseline save = baselineRepository.saveAndFlush(baseline);
        return save;
    }

    @Override
    public Baseline findById(Long id) {
        return baselineRepository.findById(id).orElseThrow(()-> new ElementNotFoundException("Baseline with id " + id + " not found"));
    }

    @Override
    public List<Baseline> findAll() {
        return baselineRepository.findAll();
    }

    @Override
    public Boolean delete(Baseline baseline) {
        long before = baselineRepository.count();
        baselineRepository.delete(baseline);
        long after = baselineRepository.count();
        return before - after == 1L;
    }

    @Override
    public List<Baseline> findByPoint(Point point) {
        return baselineRepository.findAllByPointsContains(point);
    }

    @Override
    public Boolean deleteList(List<Baseline> baselines) {
        baselineRepository.deleteAll(baselines);
        return true;
    }
}
