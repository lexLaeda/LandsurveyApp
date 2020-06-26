package com.survey.mole.controller;

import com.survey.mole.dto.LevelReferenceDto;
import com.survey.mole.dto.PairRequest;
import com.survey.mole.dto.PointDto;
import com.survey.mole.mapper.LevelReferenceMapper;
import com.survey.mole.mapper.PointMapper;
import com.survey.mole.model.survey.LevelReference;
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
    private LevelReferenceMapper levelReferenceMapper;

    @Autowired
    public PointController(PointService pointService, PointMapper pointMapper,
                           LevelReferenceMapper levelReferenceMapper) {
        this.pointService = pointService;
        this.pointMapper = pointMapper;
        this.levelReferenceMapper = levelReferenceMapper;
    }

    @GetMapping("/list")
    public List<PointDto> getPoints() {
        return this.pointService.findAll().stream()
                .map(point -> pointMapper.toDto(point)).collect(Collectors.toList());
    }


    @PostMapping("/add")
    public PointDto savePoint(@RequestBody  PointDto pointDto) {
        Point point = pointMapper.toEntity(pointDto);
        Point save = pointService.save(point);
        return pointMapper.toDto(save);
    }

    @PostMapping("/add-pair")
    public PairRequest savePointAndLR(@RequestBody PairRequest request) {

        System.out.println(request);
        PointDto pointDto = request.getPointDto();
        LevelReferenceDto levelReferenceDto = request.getLevelReferenceDto();

        LevelReference levelReference = levelReferenceMapper.toEntity(levelReferenceDto);
        Point point = pointMapper.toEntity(pointDto);
        point.setLevelReference(levelReference);

        Point saveP = pointService.save(point);
        LevelReference saveLR = saveP.getLevelReference();
        return new PairRequest(pointMapper.toDto(saveP),levelReferenceMapper.toDto(saveLR));
    }

    @PostMapping("/edit/{id}")
    public PointDto editPoint(@PathVariable("id") Long id,@RequestBody  PointDto pointDto) {
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
