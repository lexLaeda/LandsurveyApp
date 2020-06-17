package com.survey.mole.controller;

import com.survey.mole.dto.EmployeeDto;
import com.survey.mole.mapper.EmployeeMapper;
import com.survey.mole.model.worktracker.employee.Employee;
import com.survey.mole.service.worktracker.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/employee")
public class EmployeeController {

    private EmployeeService employeeService;

    private EmployeeMapper employeeMapper;

    @Autowired
    public EmployeeController(EmployeeService employeeService, EmployeeMapper employeeMapper) {
        this.employeeService = employeeService;
        this.employeeMapper = employeeMapper;
    }


    @GetMapping("/{id}")
    public EmployeeDto findCodeById(@PathVariable("id") Long id) {
        Employee byId = employeeService.findById(id);
        return employeeMapper.toDto(byId);
    }

    @GetMapping("/list")
    public List<EmployeeDto> findAll() {
        System.out.println("Тут!!!!!");
        return employeeService.findAll().stream()
                .map(employee -> employeeMapper.toDto(employee))
                .collect(Collectors.toList());
    }

    @PostMapping("/add")
    public EmployeeDto addNewCode(@RequestBody EmployeeDto employeeDto) {
        Employee employee = employeeMapper.toEntity(employeeDto);
        Employee save = employeeService.save(employee);
        return employeeMapper.toDto(save);
    }

    @PostMapping("/edit/{id}")
    public EmployeeDto editBaseline(@PathVariable("id") Long id, @RequestBody EmployeeDto employeeDto) {
        Employee employee = employeeMapper.toEntity(employeeDto);
        Employee update = employeeService.update(id, employee);
        return employeeMapper.toDto(update);
    }

    @DeleteMapping("/delete/{id}")
    public Boolean deleteBaseline(@PathVariable("id") Long id) {
        Employee byId = employeeService.findById(id);
        return employeeService.delete(byId);
    }
}
