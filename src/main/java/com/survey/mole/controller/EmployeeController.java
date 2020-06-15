package com.survey.mole.controller;

import com.survey.mole.model.worktracker.employee.Employee;
import com.survey.mole.service.worktracker.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/employee")
public class EmployeeController {

    private EmployeeService employeeService;

    @Autowired
    public EmployeeController(EmployeeService employeeService) {
        this.employeeService = employeeService;
    }


    @GetMapping("/{id}")
    public Employee findCodeById(@PathVariable("id") Long id) {
        Employee byId = employeeService.findById(id);
        return byId;
    }

    @GetMapping("/list")
    public List<Employee> findAll() {
        return employeeService.findAll();
    }

    @PostMapping("/add")
    public Employee addNewCode(@RequestBody Employee code) {
        return employeeService.save(code);
    }

    @PostMapping("/edit/{id}")
    public Employee editBaseline(@PathVariable("id") Long id, @RequestBody Employee code) {
        return employeeService.update(id, code);
    }

    @DeleteMapping("/delete/{id}")
    public Boolean deleteBaseline(@PathVariable("id") Long id) {
        Employee byId = employeeService.findById(id);
        return employeeService.delete(byId);
    }
}
