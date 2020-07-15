package com.survey.mole.service.worktracker;

import com.survey.mole.model.worktracker.Department;
import com.survey.mole.model.worktracker.employee.Employee;
import com.survey.mole.model.worktracker.employee.EmployeeHistory;
import com.survey.mole.model.worktracker.employee.WorkPeriod;
import org.springframework.stereotype.Service;

import java.time.LocalDate;

@Service
public class EmployeeHistoryServiceImpl implements EmployeeHistoryService {

    @Override
    public EmployeeHistory createEmployeeHistory(Employee employee, Department department, LocalDate start) {
        EmployeeHistory history = new EmployeeHistory();
        WorkPeriod period = createWorkPeriod(department, start);
        history.setEmployee(employee);
        history.addNewPeriod(period);
        return history;
    }

    @Override
    public EmployeeHistory updateHistory(Employee employee, Department department, LocalDate startOfNewPeriod) {
        LocalDate endOfLastPeriod = startOfNewPeriod.minusDays(1);
        EmployeeHistory employeeHistory = employee.getEmployeeHistory();
        employeeHistory.closeLastPeriod(endOfLastPeriod);
        WorkPeriod workPeriod = createWorkPeriod(department, startOfNewPeriod);
        employeeHistory.addNewPeriod(workPeriod);
        return employeeHistory;
    }


    private WorkPeriod createWorkPeriod(Department department, LocalDate start) {
        WorkPeriod period = new WorkPeriod();
        period.setDepartment(department);
        period.setStart(start);
        return period;
    }
}
