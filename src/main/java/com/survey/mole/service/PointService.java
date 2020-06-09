package com.survey.mole.service;

import com.survey.mole.model.survey.Point;

import java.util.List;

public interface PointService {
     Point save(Point point);
     Point update(Long id, Point point);
     Point findById(Long id);
     List<Point> findAll();
     void delete(Point point);
}
