package com.survey.mole.controller;

import com.survey.mole.dto.BaselineDto;
import com.survey.mole.mapper.BaselineMapper;
import com.survey.mole.model.survey.Baseline;
import com.survey.mole.service.BaselineService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/baseline")
public class BaselineController {

    private BaselineService baselineService;
    private BaselineMapper baselineMapper;

    @Autowired
    public BaselineController(BaselineService baselineService, BaselineMapper baselineMapper) {
        this.baselineService = baselineService;
        this.baselineMapper = baselineMapper;
    }


    @GetMapping("/{id}")
    public BaselineDto findBaselineById(@PathVariable("id") Long id) {
        Baseline byId = baselineService.findById(id);
        return baselineMapper.toDto(byId);
    }

    @GetMapping("/list")
    public List<BaselineDto> findAll() {
        System.out.println("А сюда заходитп");
        return baselineService.findAll().stream()
                .map(baseline -> baselineMapper.toDto(baseline))
                .peek(System.out::println)
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
        System.out.println(baselineDto.getName());
        System.out.println(baselineDto.getPointStart());
        System.out.println(baselineDto.getPointEnd());
        Baseline baseline = baselineMapper.toEntity(baselineDto);
        Baseline save = baselineService.update(id, baseline);
        return baselineMapper.toDto(save);
    }

    @DeleteMapping("/delete/{id}")
    public Boolean deleteBaseline(@PathVariable("id") Long id) {
        Baseline byId = baselineService.findById(id);
        return baselineService.delete(byId);
    }
}
