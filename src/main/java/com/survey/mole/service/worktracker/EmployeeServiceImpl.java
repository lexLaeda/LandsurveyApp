package com.survey.mole.service.worktracker;

import com.survey.mole.exception.ElementNotFoundException;
import com.survey.mole.model.worktracker.Department;
import com.survey.mole.model.worktracker.employee.Employee;
import com.survey.mole.model.worktracker.employee.EmployeeHistory;
import com.survey.mole.repository.worktracker.employee.EmployeeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
public class EmployeeServiceImpl implements EmployeeService {

    private EmployeeRepository employeeRepository;

    private EmployeeHistoryService employeeHistoryService;

    @Autowired
    public EmployeeServiceImpl(EmployeeRepository employeeRepository, EmployeeHistoryService employeeHistoryService) {
        this.employeeRepository = employeeRepository;
        this.employeeHistoryService = employeeHistoryService;
    }

    @Override
    public Employee save(Employee employee) {
        EmployeeHistory employeeHistory = employeeHistoryService.createEmployeeHistory(employee, employee.getDepartment(), LocalDate.now());
        employee.setEmployeeHistory(employeeHistory);
        return employeeRepository.saveAndFlush(employee);
    }

    @Override
    public Employee update(Long id, Employee employee) {
        Employee fromDb = findById(id);
        if (!fromDb.getDepartment().equals(employee.getDepartment())){
            EmployeeHistory employeeHistory = employeeHistoryService.updateHistory(employee, employee.getDepartment(), LocalDate.now());
            employee.setEmployeeHistory(employeeHistory);
        }
        employee.setId(id);
        return employeeRepository.saveAndFlush(employee);
    }

    @Override
    public Employee findById(Long id) {
        return employeeRepository.findById(id).orElseThrow(()->new ElementNotFoundException("Employee with id " + id + " not found"));
    }

    @Override
    public List<Employee> findAll() {
        return employeeRepository.findAll();
    }

    @Override
    public List<Employee> findByDepartment(Department department) {
        return employeeRepository.findAllByDepartment(department);
    }

    @Override
    public Boolean delete(Employee employee) {
        long before = employeeRepository.count();
        employeeRepository.delete(employee);
        long after = employeeRepository.count();
        return after-before == 1;
    }



}
