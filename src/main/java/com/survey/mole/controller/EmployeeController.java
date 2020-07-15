package com.survey.mole.controller;

import com.survey.mole.dto.EmployeeDto;
import com.survey.mole.mapper.EmployeeMapper;
import com.survey.mole.model.worktracker.Department;
import com.survey.mole.model.worktracker.employee.Employee;
import com.survey.mole.service.worktracker.DepartmentService;
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

    private DepartmentService departmentService;

    @Autowired
    public EmployeeController(EmployeeService employeeService, EmployeeMapper employeeMapper, DepartmentService departmentService) {
        this.employeeService = employeeService;
        this.employeeMapper = employeeMapper;
        this.departmentService = departmentService;
    }


    @GetMapping("/{id}")
    public EmployeeDto findEmployeeById(@PathVariable("id") Long id) {
        Employee byId = employeeService.findById(id);
        return employeeMapper.toDto(byId);
    }

    @GetMapping("/list")
    public List<EmployeeDto> findAll() {

        return employeeService.findAll().stream()
                .map(employee -> employeeMapper.toDto(employee))
                .collect(Collectors.toList());
    }

    @GetMapping("/list/")
    public List<EmployeeDto> findAllByDepartment(@RequestParam("dep") Long departmentId){
        Department department = departmentService.findById(departmentId);
        return employeeService.findByDepartment(department).stream()
                .map(employee -> employeeMapper.toDto(employee))
                .collect(Collectors.toList());
    }

    @PostMapping("/add")
    public EmployeeDto addNewEmployee(@RequestBody EmployeeDto employeeDto) {
        Employee employee = employeeMapper.toEntity(employeeDto);
        Employee save = employeeService.save(employee);
        return employeeMapper.toDto(save);
    }

    @PostMapping("/edit/{id}")
    public EmployeeDto editEmployee(@PathVariable("id") Long id, @RequestBody EmployeeDto employeeDto) {
        Employee employee = employeeMapper.toEntity(employeeDto);
        Employee update = employeeService.update(id, employee);
        return employeeMapper.toDto(update);
    }

    @DeleteMapping("/delete/{id}")
    public Boolean deleteEmployee(@PathVariable("id") Long id) {
        Employee byId = employeeService.findById(id);
        return employeeService.delete(byId);
    }
}
