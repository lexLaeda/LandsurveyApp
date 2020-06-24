package com.survey.mole.controller;

import com.survey.mole.dto.BaselineDto;
import com.survey.mole.mapper.BaselineMapper;
import com.survey.mole.model.survey.Baseline;
import com.survey.mole.model.survey.Point;
import com.survey.mole.service.BaselineService;
import com.survey.mole.service.PointService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/baseline")
public class BaselineController {

    private BaselineService baselineService;
    private BaselineMapper baselineMapper;
    private PointService pointService;

    @Autowired
    public BaselineController(BaselineService baselineService, BaselineMapper baselineMapper,
                              PointService pointService) {
        this.baselineService = baselineService;
        this.baselineMapper = baselineMapper;
        this.pointService = pointService;
    }


    @GetMapping("/{id}")
    public BaselineDto findBaselineById(@PathVariable("id") Long id) {
        Baseline byId = baselineService.findById(id);
        return baselineMapper.toDto(byId);
    }

    @GetMapping("/list")
    public List<BaselineDto> findAll() {
        return baselineService.findAll().stream()
                .map(baseline -> baselineMapper.toDto(baseline))
                .peek(System.out::println)
                .collect(Collectors.toList());
    }

    @GetMapping("/by_point/{id}")
    public List<BaselineDto> findByPoint(@PathVariable("id") Long id){
        Point byId = pointService.findById(id);
        return baselineService.findByPoint(byId).stream()
                .map(baseline -> baselineMapper.toDto(baseline))
                .collect(Collectors.toList());
    }

    @PostMapping("/add")
    public BaselineDto addNewBaseline(@RequestBody BaselineDto baselineDto) {
        Baseline baseline = baselineMapper.toEntity(baselineDto);
        Baseline save = baselineService.save(baseline);
        return baselineMapper.toDto(save);
    }

    @PostMapping("/edit/{id}")
    public BaselineDto editBaseline(@PathVariable("id") Long id, @RequestBody BaselineDto baselineDto) {

        Baseline baseline = baselineMapper.toEntity(baselineDto);
        Baseline save = baselineService.update(id, baseline);
        return baselineMapper.toDto(save);
    }

    @DeleteMapping("/delete/{id}")
    public Boolean deleteBaseline(@PathVariable("id") Long id) {
        Baseline byId = baselineService.findById(id);
        return baselineService.delete(byId);
    }

    @PostMapping("/delete-list")
    public Boolean deleteBaselineList(@RequestBody List<BaselineDto> baselines) {
        System.out.println("ТУТУТУТУ");
        System.out.println(baselines);
        List<Baseline> collect = baselines.stream().map(baselineDto -> baselineMapper.toEntity(baselineDto)).collect(Collectors.toList());
        Boolean aBoolean = baselineService.deleteList(collect);
        System.out.println(aBoolean);
        return aBoolean;
    }
}
