package com.survey.mole.controller;

import com.survey.mole.model.survey.Point;
import com.survey.mole.service.PointService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/point")
public class PointController {

    private PointService pointService;

    @Autowired
    public PointController(PointService pointService) {
        this.pointService = pointService;
    }

    @GetMapping("/list")
    public List<Point> getPoints(){
        return this.pointService.findAll();
    }

    @PostMapping("/new")
    public Point savePoint(Point point){
        return pointService.save(point);
    }


}
