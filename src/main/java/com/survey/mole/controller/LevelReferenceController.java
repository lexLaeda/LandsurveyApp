package com.survey.mole.controller;

import com.survey.mole.dto.LevelReferenceDto;
import com.survey.mole.mapper.LevelReferenceMapper;
import com.survey.mole.model.survey.LevelReference;
import com.survey.mole.service.LevelReferenceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/level-reference")
public class LevelReferenceController {

    private LevelReferenceService levelReferenceService;
    private LevelReferenceMapper levelReferenceMapper;

    @Autowired
    public LevelReferenceController(LevelReferenceService levelReferenceService,
                                    LevelReferenceMapper levelReferenceMapper) {
        this.levelReferenceService = levelReferenceService;
        this.levelReferenceMapper = levelReferenceMapper;
    }

    @GetMapping("/{id}")
    public LevelReferenceDto findBaselineById(@PathVariable("id") Long id) {
        LevelReference byId = levelReferenceService.findById(id);
        return levelReferenceMapper.toDto(byId);
    }

    @GetMapping("/list")
    public List<LevelReferenceDto> findAll() {
        return levelReferenceService.findAll().stream()
                .map(levelReference -> levelReferenceMapper.toDto(levelReference))
                .peek(System.out::println)
                .collect(Collectors.toList());
    }

    @PostMapping("/add")
    public LevelReferenceDto addNewBaseline(@RequestBody LevelReferenceDto levelReferenceDto) {
        LevelReference levelReference = levelReferenceMapper.toEntity(levelReferenceDto);
        LevelReference save = levelReferenceService.save(levelReference);
        return levelReferenceMapper.toDto(save);
    }

    @PostMapping("/edit/{id}")
    public LevelReferenceDto editBaseline(@PathVariable("id") Long id,@RequestBody  LevelReferenceDto levelReferenceDto) {
        LevelReference levelReference = levelReferenceMapper.toEntity(levelReferenceDto);
        LevelReference save = levelReferenceService.update(id, levelReference);
        return levelReferenceMapper.toDto(save);
    }

    @DeleteMapping("/delete/{id}")
    public Boolean deleteBaseline(@PathVariable("id") Long id) {
        LevelReference byId = levelReferenceService.findById(id);
        return levelReferenceService.delete(byId);
    }
}
