package com.survey.mole.controller;

import com.survey.mole.dto.PointDto;
import com.survey.mole.mapper.PointMapper;
import com.survey.mole.model.survey.Point;
import com.survey.mole.service.PointService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/point")
public class PointController {

    private PointService pointService;

    private PointMapper pointMapper;

    @Autowired
    public PointController(PointService pointService, PointMapper pointMapper) {
        this.pointService = pointService;
        this.pointMapper = pointMapper;
    }

    @GetMapping("/list")
    public List<PointDto> getPoints() {
        return this.pointService.findAll().stream()
                .map(point -> pointMapper.toDto(point)).collect(Collectors.toList());
    }

    @PostMapping("/new")
    public PointDto savePoint(PointDto pointDto) {
        Point point = pointMapper.toEntity(pointDto);
        Point save = pointService.save(point);
        return pointMapper.toDto(save);
    }

    @PostMapping("/edit/{id}")
    public PointDto editPoint(@PathVariable("id") Long id, PointDto pointDto) {
        Point point = pointMapper.toEntity(pointDto);
        Point update = pointService.update(id, point);
        return pointMapper.toDto(update);
    }

    @DeleteMapping("/delete/{id}")
    public Boolean deletePoint(@PathVariable("id") Long id) {
        Point point = pointService.findById(id);
        return pointService.delete(point);
    }
}
