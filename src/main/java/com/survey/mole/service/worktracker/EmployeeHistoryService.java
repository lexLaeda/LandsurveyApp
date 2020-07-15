package com.survey.mole.service.worktracker;

import com.survey.mole.model.worktracker.Department;
import com.survey.mole.model.worktracker.employee.Employee;
import com.survey.mole.model.worktracker.employee.EmployeeHistory;

import java.time.LocalDate;

public interface EmployeeHistoryService {
    EmployeeHistory createEmployeeHistory(Employee employee, Department department, LocalDate start);

    EmployeeHistory updateHistory(Employee employee, Department newDepartment, LocalDate startOfNewPeriod);

}
