package com.survey.mole.service;

import com.survey.mole.exception.PointNotFoundException;
import com.survey.mole.model.survey.Point;
import com.survey.mole.repository.PointRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PointServiceImpl implements PointService {

    private PointRepository pointRepository;

    @Autowired
    public PointServiceImpl(PointRepository pointRepository) {
        this.pointRepository = pointRepository;
    }

    @Override
    public Point save(Point point) {
        return pointRepository.saveAndFlush(point);
    }

    @Override
    public Point update(Long id, Point point) {
        Point byId = findById(id);
        point.setId(byId.getId());
        return pointRepository.saveAndFlush(point);
    }

    @Override
    public Point findById(Long id) {
        return pointRepository.findById(id)
                .orElseThrow(PointNotFoundException::new);
    }

    @Override
    public List<Point> findAll() {
        return pointRepository.findAll();
    }

    @Override
    public void delete(Point point) {
        pointRepository.delete(point);
    }
}