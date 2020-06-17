package com.survey.mole.controller;

import com.survey.mole.dto.DepartmentDto;
import com.survey.mole.mapper.DepartmentMapper;
import com.survey.mole.model.worktracker.Department;
import com.survey.mole.service.worktracker.DepartmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/department")
public class DepartmentController {

    private DepartmentService departmentService;

    private DepartmentMapper departmentMapper;

    @Autowired
    public DepartmentController(DepartmentService departmentService, DepartmentMapper departmentMapper) {
        this.departmentService = departmentService;
        this.departmentMapper = departmentMapper;
    }

    @GetMapping("/{id}")
    public DepartmentDto findCodeById(@PathVariable("id") Long id) {
        Department byId = departmentService.findById(id);
        return departmentMapper.toDto(byId);
    }

    @GetMapping("/list")
    public List<DepartmentDto> findAll() {
        return departmentService.findAll().stream()
                .map(department -> departmentMapper.toDto(department))
                .collect(Collectors.toList());
    }

    @PostMapping("/add")
    public DepartmentDto addNewCode(@RequestBody DepartmentDto departmentDto) {
        Department department = departmentMapper.toEntity(departmentDto);
        Department save = departmentService.save(department);
        return departmentMapper.toDto(save);
    }

    @PostMapping("/edit/{id}")
    public DepartmentDto editBaseline(@PathVariable("id") Long id, @RequestBody DepartmentDto departmentDto) {
        Department department = departmentMapper.toEntity(departmentDto);
        Department update = departmentService.update(id, department);
        return departmentMapper.toDto(update);
    }

    @DeleteMapping("/delete/{id}")
    public Boolean deleteBaseline(@PathVariable("id") Long id) {
        Department byId = departmentService.findById(id);
        return departmentService.delete(byId);
    }
}
