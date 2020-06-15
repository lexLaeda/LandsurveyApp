package com.survey.mole.controller;

import com.survey.mole.model.worktracker.Department;
import com.survey.mole.service.worktracker.DepartmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/department")
public class DepartmentController {

    private DepartmentService departmentService;

    @Autowired
    public DepartmentController(DepartmentService departmentService) {
        this.departmentService = departmentService;
    }

    @GetMapping("/{id}")
    public Department findCodeById(@PathVariable("id") Long id) {
        Department byId = departmentService.findById(id);
        return byId;
    }

    @GetMapping("/list")
    public List<Department> findAll() {
        return departmentService.findAll();
    }

    @PostMapping("/add")
    public Department addNewCode(@RequestBody Department department) {
        return departmentService.save(department);
    }

    @PostMapping("/edit/{id}")
    public Department editBaseline(@PathVariable("id") Long id, @RequestBody Department department) {
        return departmentService.update(id, department);
    }

    @DeleteMapping("/delete/{id}")
    public Boolean deleteBaseline(@PathVariable("id") Long id) {
        Department byId = departmentService.findById(id);
        return departmentService.delete(byId);
    }
}
