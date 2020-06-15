package com.survey.mole.service.worktracker;

import com.survey.mole.model.worktracker.Department;
import com.survey.mole.model.worktracker.employee.Employee;

import java.util.List;

public interface EmployeeService {

    Employee save(Employee employee);

    Employee update(Long id, Employee employee);

    Employee findById(Long id);

    List<Employee> findAll();

    List<Employee> findByDepartment(Department department);

    Boolean delete(Employee employee);
}
