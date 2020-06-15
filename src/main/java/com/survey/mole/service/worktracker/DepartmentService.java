package com.survey.mole.service.worktracker;

import com.survey.mole.model.worktracker.Department;
import com.survey.mole.model.worktracker.employee.Employee;

import java.util.List;

public interface DepartmentService {
    Department save(Department department);

    Department update(Long id, Department department);

    Department findById(Long id);

    List<Department> findAll();

    Boolean delete(Department department);
}
